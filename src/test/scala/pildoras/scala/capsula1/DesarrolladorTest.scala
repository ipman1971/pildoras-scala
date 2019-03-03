package net.bluetab.pildoras.scala.capsula1

import org.scalatest._

class DesarrolladorTest extends FunSuite {

  /**
  * Las case class crean un método factoria con el nombre de la clase y el mismo numero y tipo de parametros
    */
  test("crear instancias sin operador new") {
    val developer = Desarrollador("Pepe", "Bluetab", List("java", "scala"), 0)

    assert(developer != null)
  }

  test("los parametros del constructor son miembros de la clase") {
    val developer = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)

    assert(developer.nombre == "Pepe")
    assert(developer.empresa == "Bluetab")
    assert(developer.tecnologias == Seq("java", "scala"))
    assert(developer.experiencia == 20)
  }

  test("implementación automatica para toString") {
    val developer = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)

    assert(developer.toString == "Desarrollador(Pepe,Bluetab,List(java, scala),20)")
  }

  test("implementación automatica para hashCode") {
    val developer = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)

    assert(developer.hashCode() > 0)
  }

  test("implementación automatica para equals") {
    val developer1 = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)
    val developer2 = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)

    assert(developer1.equals(developer2))
    assert(developer1==developer2,false)
  }

  test("implementación automatica del metodo copy") {
    val developer1 = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)
    val developer2 = developer1.copy()

    assert(developer2.nombre == "Pepe")
    assert(developer2.empresa == "Bluetab")
    assert(developer2.tecnologias == Seq("java", "scala"))
    assert(developer2.experiencia == 20)
  }

  test("copia de un objeto modificando atributos") {
    val developer1 = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)
    val developer2 = developer1.copy(nombre = "Jesus", experiencia = 4)

    assert(developer2.nombre == "Jesus")
    assert(developer2.empresa == "Bluetab")
    assert(developer2.tecnologias == Seq("java", "scala"))
    assert(developer2.experiencia == 4)
  }

  test("usando metodo apply") {
    val developer = Desarrollador.apply("Pepe", "Bluetab", Seq("java", "scala"), 20)

    assert(developer.nombre == "Pepe")
    assert(developer.empresa == "Bluetab")
    assert(developer.tecnologias == Seq("java", "scala"))
    assert(developer.experiencia == 20)
  }

  test("usando metodo unapply") {
    val developer = Desarrollador("Pepe", "Bluetab", Seq("java", "scala"), 20)
    val someDeveloper = Desarrollador.unapply(developer)

    assert(someDeveloper.map(_._1) == Some("Pepe"))
    assert(someDeveloper.map(_._2) == Some("Bluetab"))
    assert(someDeveloper.map(_._3) == Some(Seq("java", "scala")))
    assert(someDeveloper.map(_._4) == Some(20))
  }

}
