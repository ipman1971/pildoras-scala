package net.bluetab.pildoras.scala.capsula1

case class Desarrollador(nombre: String,
                         empresa: String,
                         tecnologias: Seq[String],
                         experiencia: Int)

abstract class Persona(sexo: String)

sealed trait Consultoras {
  def empresa: String
}

case class Developer(val nombre: String, empresa: String, tecnologias: Seq[String], experiencia: Int, sexo: String)
  extends Persona(sexo) with Consultoras
