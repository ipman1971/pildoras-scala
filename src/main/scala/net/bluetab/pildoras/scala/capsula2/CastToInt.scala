package net.bluetab.pildoras.scala.capsula2

object CastToInt {

  def toInt1(valor: String): Int = {
    valor.toInt
  }

  def toInt2(valor: String): Int = {
    try {
      valor.toInt
    }
    catch {
      case _: Throwable =>
        println("no se ha podido convertir el valor a Int")
        0
    }
  }

  def toInt3(valor: String): Int = {
    if (valor != null && valor.nonEmpty) valor.toInt else 0
  }

  def toInt4(valor: String): Option[Int] = {
    try {
      Some(valor.toInt)
    }
    catch {
      case _: Throwable => None
    }
  }

}
