package net.bluetab.pildoras.scala.capsula2

case class Usuario(id: Int, nonbre: String, apellidos: String, edad: Int, genero: Option[String])

case class PathInfo(localPath: String, remotePath: String)

object UserRepository {

  // inicializo el repositorio de ejemplo
  private val usuarios: Map[Int, Usuario] = {
    Map(1 -> Usuario(1, "Jesus", "Mazo", 28, Some("hombre")),
      2 -> Usuario(2, "Pepe", "Lopez", 41, None))
  }

  def findById(id: Int): Option[Usuario] = {
    usuarios.get(id)
  }

  def findAll: Iterable[Usuario] = {
    usuarios.values
  }

}
