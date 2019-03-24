package net.bluetab.pildoras.scala.capsula3

import org.scalatest.FunSuite
import net.bluetab.pildoras.scala.capsula3.Recursive._

class RecursiveTest extends FunSuite {

  test("suma recursiva") {
    val lista: Seq[Int] = Seq(1, 2, 3, 4, 5)
    assert(sum(lista) == 15)
  }

  test("suma tail recursive") {
    val lista: Seq[Int] = Seq(1, 2, 3, 4, 5)
    assert(tailSum(lista, sum) == 15)
  }

  test("suma tail recursive autentica") {
    val lista: Seq[Int] = Seq(1, 2, 3, 4, 5)
    assert(tailRecSum(lista) == 15)
  }

  test("suma tail recursive estilo funcional") {
    val lista: Seq[Int] = Seq(1, 2, 3, 4, 5)
    assert(tailRecSumFunctionalStyle(lista) == 15)
  }

  test("compute con add de Int") {
    val lista: Seq[Int] = Seq(1, 2, 3, 4, 5)
    assert(compute[Int](lista, add, 0) == 15)
  }

  test("compute con product de Double") {
    val lista: Seq[Double] = Seq(1.0, 2.0, 3.0, 4.0, 5.0)
    assert(compute[Double](lista, product, 1.0) == 120.0)
  }

  test("compute con lambda de Double") {
    val lista: Seq[Double] = Seq(1.0, 2.0, 3.0, 4.0, 5.0)
    assert(compute[Double](lista, (x: Double, y: Double) => x * y, 1.0) == 120.0)
  }

}
