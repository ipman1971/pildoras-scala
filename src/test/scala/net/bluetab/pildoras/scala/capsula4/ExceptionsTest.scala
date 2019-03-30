package net.bluetab.pildoras.scala.capsula4

import org.scalatest.FunSuite
import scala.util.control.Exception._

class ExceptionsTest extends FunSuite {

  test("handlerException con operacion soportada") {
    val catchConfig: Seq[Catch[_]] = Seq(catching(classOf[NumberFormatException]))
    assert(handlerException(catchConfig)(1000 / 2) == 500)
  }

  test("handlerException con operacion no soportada") {
    val catchConfig: Seq[Catch[_]] = Seq(catching(classOf[ArithmeticException]))
    assertThrows[ArithmeticException](handlerException(catchConfig)(0 / 0) == 0)
  }

  test("handlerException con operacion no soportada y valor por defecto") {
    val catchConfig: Seq[Catch[_]] = Seq(failAsValue(classOf[ArithmeticException])(0))
    assert(handlerException(catchConfig)(0 / 0) == 0)
  }

  test("handlerException con operacion no soportada y con varios valores por defecto") {
    val catchConfig: Seq[Catch[_]] = Seq(failAsValue(classOf[ArithmeticException])(0),
      failAsValue(classOf[NullPointerException])(-1))
    val param: String = null
    assert(handlerException(catchConfig)(param.toLowerCase) == -1)
  }

}
