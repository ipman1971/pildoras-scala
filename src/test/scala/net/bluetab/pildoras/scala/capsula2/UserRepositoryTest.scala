package net.bluetab.pildoras.scala.capsula2

import org.scalatest.FunSuite

class UserRepositoryTest extends FunSuite {

  test("probando el metodo isDefined con id correcto") {
    val usuario: Option[Usuario] = UserRepository.findById(1)
    assert(usuario.isDefined)
  }

  test("probando el metodo isDefined con id incorrecto") {
    val usuario: Option[Usuario] = UserRepository.findById(100)
    assert(usuario.isDefined==false)
    assert(usuario==None)
  }

  test("probando el metodo getOrElse cuando existe el valor") {
    val usuario: Option[Usuario] = UserRepository.findById(1)
    assert(usuario.get.genero.getOrElse("no especificado")=="hombre")
  }

  test("probando el metodo getOrElse cuando no existe el valor") {
    val usuario: Option[Usuario] = UserRepository.findById(2)
    assert(usuario.get.genero.getOrElse("no especificado")=="no especificado")
  }

  test("probando el metodo orElse cuando no existe valor") {
    val defaultPathInfo: Option[PathInfo] = Some(PathInfo(localPath = "/home/pepe", remotePath = "/opt/pepe"))
    val nullPathInfo: Option[PathInfo] = None

    val path: Option[PathInfo] = nullPathInfo.orElse(defaultPathInfo)
    assert(path==defaultPathInfo)
  }

  test("probando pattern matcher sobre un option que existe") {
    val usuario: Option[Usuario] = UserRepository.findById(1)
    assert(verificaGenero(usuario.get.genero) =="genero: hombre")
  }

  test("probando pattern matcher sobre un option que no existe") {
    val usuario: Option[Usuario] = UserRepository.findById(2)
    assert(verificaGenero(usuario.get.genero) =="genero no especificado")
  }

  test("probando map sobre un option con valor") {
    val usuario: Option[Usuario] = UserRepository.findById(1)
    assert(usuario.map(_.edad) == Some(28))
    assert(usuario.map(_.genero) == Some(Some("hombre")))
  }

  test("probando map sobre un option con valor None") {
    val usuario: Option[Usuario] = UserRepository.findById(2)
    assert(usuario.map(_.genero) == Some(None))
  }

  test("probando map sobre una coleccion de options") {
    val blackList: Seq[Option[String]] = Seq(Some("LuisFer"),None,Some("Carus"),None,None,Some("el falso Gabriel"))
    assert(blackList.map(_.map(_.toUpperCase))==Seq(Some("LUISFER"),None,Some("CARUS"),None,None,Some("EL FALSO GABRIEL")))
  }

  test("probando flatmap sobre un option con valor") {
    val usuario: Option[Usuario] = UserRepository.findById(1)

    assert(usuario.map(_.genero) == Some(Some("hombre")))
    assert(usuario.flatMap(_.genero) == Some("hombre"))
  }

  test("probando flatmap sobre un option con valor None") {
    val usuario: Option[Usuario] = UserRepository.findById(2)

    assert(usuario.flatMap(_.genero) == None)
  }

  test("probando flatmap sobre una coleccion de options") {
    val blackList: Seq[Option[String]] = Seq(Some("LuisFer"),None,Some("Carus"),None,None,Some("el falso Gabriel"))
    assert(blackList.flatMap(option => option.map(_.toUpperCase))==Seq("LUISFER","CARUS","EL FALSO GABRIEL"))
  }

  test("probando filter sobre un option") {
    val usuario1: Option[Usuario] = UserRepository.findById(1)
    val usuario2: Option[Usuario] = UserRepository.findById(2)

    assert(usuario1.filter(_.edad < 30)== usuario1)
    assert(usuario2.filter(_.edad < 30)== None)
  }

  private def verificaGenero(genero: Option[String]): String = {
    genero match {
      case Some(tipo) => s"genero: $tipo"
      case None => s"genero no especificado"
    }
  }


}
