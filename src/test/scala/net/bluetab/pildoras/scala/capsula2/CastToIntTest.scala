package net.bluetab.pildoras.scala.capsula2

import org.scalatest.FunSuite

class CastToIntTest extends FunSuite {

  test("usando toInt1 con valor correcto") {
    assert(CastToInt.toInt1("200") == 200)
  }

  test("usando toInt1 con valor incorrecto") {
    assertThrows[NumberFormatException](CastToInt.toInt1("pepe"))
  }

  test("usando toInt1 con valor null") {
    assertThrows[NumberFormatException](CastToInt.toInt1(null))
  }

  test("usando toInt1 con valor vacio") {
    assertThrows[NumberFormatException](CastToInt.toInt1(""))
  }

  test("usando toInt2 con valor correcto") {
    assert(CastToInt.toInt2("200") == 200)
  }

  test("usando toInt2 con valor incorrecto") {
    assert(CastToInt.toInt2("pepe") == 0)
  }

  test("usando toInt2 con valor null") {
    assert(CastToInt.toInt2(null) == 0)
  }

  test("usando toInt2 con valor vacio") {
    assert(CastToInt.toInt2("") == 0)
  }

  test("usando toInt3 con valor correcto") {
    assert(CastToInt.toInt3("200") == 200)
  }

  test("usando toInt3 con valor incorrecto") {
    assertThrows[NumberFormatException](CastToInt.toInt3("pepe"))
  }

  test("usando toInt3 con valor null") {
    assert(CastToInt.toInt3(null) == 0)
  }

  test("usando toInt3 con valor vacio") {
    assert(CastToInt.toInt3("") == 0)
  }

  test("usando toInt4 con valor correcto") {
    val result = CastToInt.toInt4("200")

    assert(result == Some(200))
    assert(extractValue(result) == 200)
  }

  test("usando toInt4 con valor incorrecto") {
    val result = CastToInt.toInt4("pepe")

    assert(result == None)
    assert(extractValue(result) == 0)
  }

  test("usando toInt4 con valor null") {
    val result = CastToInt.toInt4(null)

    assert(result == None)
    assert(extractValue(result) == 0)
  }

  test("usando toInt4 con valor vacio") {
    val result = CastToInt.toInt4("")

    assert(result.isEmpty)
    assert(extractValue(result) == 0)
  }

  private def extractValue(value: Option[Int]): Int = {
    value.getOrElse(0)
  }

}
