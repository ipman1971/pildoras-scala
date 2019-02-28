package net.bluetab.pildoras.scala.capsula1

class CustomPersona(_nombre: String, _sexo: String) extends Serializable {

  val nombre =_nombre
  val sexo = _sexo

  override def equals(otro: Any): Boolean = {
    otro match {
      case instance: CustomPersona => (instance canEqual this) &&
          nombre == instance.nombre && sexo == instance.sexo
      case _ => false
    }
  }

  def canEqual(other: Any): Boolean = {
    other.isInstanceOf[CustomPersona]
  }

  override def hashCode(): Int = {
    val estado = Seq(nombre, sexo)
    estado.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString: String = {
    s"Persona($nombre,$sexo)"
  }

  def copy(nombre: String = nombre, sexo: String = sexo): CustomPersona = {
    new CustomPersona(nombre, sexo)
  }

}
