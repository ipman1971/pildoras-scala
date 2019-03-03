package net.bluetab.pildoras.scala.capsula1

trait BBVAUser {
  val name: String
  val pass: String
}

class Externo(val name: String, val pass: String) extends BBVAUser

class Interno(val name: String, val pass: String, val level: String) extends BBVAUser

object Externo {
  def unapply(instance: Externo): Option[(String, String)] = {
    Some((instance.name,instance.pass))
  }
}

object Interno {
  def unapply(instance: Interno): Option[(String, String, String)] = {
    Some((instance.name,instance.pass,instance.level))
  }
}

trait BBVAMatcher {

  def analize(user: BBVAUser): String = user match {
    case Externo(name, _) => s"contractor: $name"
    case Interno(name, _, level) => s"empleado: $name -> nivel: $level"
  }

}
