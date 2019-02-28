package net.bluetab.pildoras.scala.capsula1

import org.scalatest._

class PersonaTest extends FunSuite {

  test("acceso a propiedad nombre") {
    val persona1 = new CustomPersona("Natalia","mujer")
    val persona2 = Persona("Pepe","hombre")

    assert(persona1.nombre=="Natalia")
    assert(persona2.nombre=="Pepe")
  }

  test("acceso a propiedad sexo") {
    val persona1 = new CustomPersona("Natalia","mujer")
    val persona2 = Persona("Pepe","hombre")

    assert(persona1.sexo=="mujer")
    assert(persona2.sexo=="hombre")
  }

  test("test sobre metodo canEqual") {
    val persona1 = new CustomPersona("Natalia","mujer")
    val persona2 = Persona("Pepe","hombre")

    assert(persona1.canEqual(persona2)==true)
  }

  test("test sobre metodo copy") {
    val persona1 = new CustomPersona("Natalia","mujer")
    val persona2 = persona1.copy(nombre="Patricia")

    assert(persona1.nombre=="Natalia")
    assert(persona2.nombre=="Patricia")
  }

  test("test sobre metodo toString") {
    val persona1 = new CustomPersona("Natalia","mujer")

    assert(persona1.toString=="Persona(Natalia,mujer)")
  }

  test("test sobre metodo equals") {
    val persona1 = Persona("Natalia","mujer")
    val persona2 = Persona("Pepe","hombre")
    val persona3 = Persona("Natalia","mujer")

    assert(persona1.equals(persona2)==false)
    assert(persona1.equals(persona3)==true)
  }

  test("test sobre metodo hashCode") {
    val persona1 = Persona("Natalia","mujer")
    val persona2 = Persona("Pepe","hombre")

    assert(persona1.hashCode!=persona2.hashCode)
  }

  test("test sobre unapply") {
    val persona1 = Persona("Natalia","mujer")

    def pattern(ref: Any): String = {
      ref match {
        case p: CustomPersona => p.toString
        case _ => "no soy persona"
      }
    }

    assert(pattern(persona1)=="Persona(Natalia,mujer)")
    assert(pattern(1000)=="no soy persona")
  }

  test("test sobre metodo apply") {
    val persona1 = Persona("Patricia","mujer")

    assert(persona1.isInstanceOf[CustomPersona]==true)
  }

}
