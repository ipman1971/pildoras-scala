package net.bluetab.pildoras.scala.capsula1

case class Desarrollador(nombre: String, empresa: String, tecnologias: Seq[String], experiencia: Int)

sealed trait Consultoras {
  def empleados: Int
}

case class Bluetab(empleados: Int, clientes: Int) extends Consultoras
case class Everis(empleados: Int,paises: Int) extends Consultoras

case class Persona(nombre: String, sexo: String)

