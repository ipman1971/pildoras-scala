package pildoras.scala.capsula1

import net.bluetab.pildoras.scala.capsula1.{BBVAMatcher, BBVAUser, Externo, Interno}
import org.scalatest._

class BBVAUserTest extends FunSuite with BBVAMatcher {

  test("verificacion de tipo BBVAUser") {
    val externo = new Externo(name="Pepe", pass="1234")
    val interno = new Interno(name="Luis", pass="3334", level = "D")

    assert(externo.isInstanceOf[BBVAUser])
    assert(interno.isInstanceOf[BBVAUser])
  }

  test("usando los extractores") {
    val externo = new Externo(name="Pepe", pass="1234")
    val interno = new Interno(name="Luis", pass="3334", level = "D")

    val extractorExterno = Externo.unapply(externo)
    val extractorInterno = Interno.unapply(interno)

    assert(extractorExterno==Some("Pepe","1234"))
    assert(extractorInterno==Some("Luis","3334","D"))
  }

  test("pattern matcher sobre Externo") {
    val externo = new Externo(name="Pepe", pass="1234")

    assert(analize(externo)=="contractor: Pepe")
  }

  test("pattern matcher sobre Interno") {
    val interno = new Interno(name="Luis", pass="3334", level = "D")

    assert(analize(interno)=="empleado: Luis -> nivel: D")
  }

  test("pattern por tipos") {

    def otherAnalize(objeto: Any): String = objeto match {
      case a: Externo => "soy un externo"
      case b: Interno => "soy un interno"
      case _ => "no soy de este mundo"
    }

    assert(otherAnalize(new Externo(name="Pepe", pass="1234"))=="soy un externo")
    assert(otherAnalize(new Interno(name="Luis", pass="3334", level = "D"))=="soy un interno")
    assert(otherAnalize("sdfds")=="no soy de este mundo")

  }

}
