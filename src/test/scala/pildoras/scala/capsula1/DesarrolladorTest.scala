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

  test("crear con operador new aunque no es buen de buen estilo") {
    val developer = new Desarrollador("Pepe", "Bluetab", List("java", "scala"), 0)

    assert(developer != null)
  }

  test("propiedades accesibles") {
    val developer = Desarrollador("Pepe", "Bluetab", List("java", "scala"), 0)

    assert(developer.nombre == "Pepe")
    assert(developer.experiencia == 0)
  }

  test("hashcode, equals y toString generados por Scala") {
    val developer1 = Desarrollador("Pepe", "Bluetab", List("java", "scala"), 0)
    val developer2 = Desarrollador("Patricia", "Everis", List("java", "scala"), 5)
    val developer3 = Desarrollador("Patricia", "Everis", List("java", "scala"), 5)

    assert(developer1.hashCode != developer2.hashCode)
    assert(developer1.equals(developer2) == false)
    assert(developer2.equals(developer3) == true)
    assert(developer2.toString == "Desarrollador(Patricia,Everis,List(java, scala),5)")
  }

  test("uso de copy") {
    val developer = Desarrollador("Pepe", "Bluetab", List("java", "scala"), 0)
    val newDeveloper = developer.copy(tecnologias = List("docker", "ansible"), experiencia = 2)

    assert(newDeveloper.nombre == "Pepe")
    assert(newDeveloper.tecnologias == List("docker", "ansible"))
    assert(newDeveloper.experiencia == 2)
  }

  test("uso en pattern match") {
    val consultora1=Bluetab(250,50)
    val consultora2=Everis(8000,20)

    def logConsultoras(consultora: Consultoras): String = {
      consultora match {
        case objeto1: Bluetab => s"clientes: ${objeto1.clientes}, empleados: ${objeto1.empleados}"
        case objeto2: Everis => s"paises: ${objeto2.paises}"
      }
    }

    assert(logConsultoras(consultora1)=="clientes: 50, empleados: 250")
    assert(logConsultoras(consultora2)=="paises: 20")
  }

  test("creando a partir de un apply en forma de lambda") {
    val lambdaCreator: (String, String, Seq[String], Int) => Desarrollador = Desarrollador.apply _

    val desarrollador= lambdaCreator("Natalia","Everis",List("java","scala"),3)

    assert(desarrollador.nombre=="Natalia")
    assert(desarrollador.tecnologias.size==2)
  }

  test("creando a partir de un apply") {
    val tupleCreator: ((String, String, Seq[String], Int)) => Desarrollador = Desarrollador.tupled

    val desarrollador= tupleCreator(("Patri","Everis",List("java","scala","R"),6))

    assert(desarrollador.nombre=="Patri")
    assert(desarrollador.tecnologias.size==3)
  }

  test("funciones ofrecidas por el trait Product") {
    val tupleCreator: ((String, String, Seq[String], Int)) => Desarrollador = Desarrollador.tupled
    val desarrollador= tupleCreator(("Patri","Everis",List("java","scala","R"),6))

    assert(desarrollador.productArity==4)
    assert(desarrollador.productElement(0).asInstanceOf[String]=="Patri")
    assert(desarrollador.productElement(2).asInstanceOf[List[String]]==List("java","scala","R"))
    assert(desarrollador.productIterator.filter(propiedad => propiedad.isInstanceOf[List[String]])
      .flatMap(l => l.asInstanceOf[List[String]]).mkString(",")=="java,scala,R")
    assert(desarrollador.productPrefix=="Desarrollador")
  }

}
