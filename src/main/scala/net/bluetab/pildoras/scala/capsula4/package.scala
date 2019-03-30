package net.bluetab.pildoras.scala

import java.io.InputStream
import java.net.URL

import scala.util.control.Exception._
import scala.util.{Failure, Success, Try}

class JavaStyleException(msg: String, cause: Throwable) extends Exception(msg, cause) {
  def this() = this("", null)

  def this(msg: String) = this(msg, null)

  def this(cause: Throwable) = this("", cause)
}

case class SparkJobException(msg: String, errorCode: Int = -1, cause: Option[Throwable] = None)
  extends RuntimeException(msg)

//case object JOB_KO extends SparkJobException("el job ha terminado de forma incorrecta")

package object capsula4 {

  val exception1: SparkJobException = SparkJobException("el job ha terminado de forma incorrecta")
  val exception2: SparkJobException = SparkJobException("tipo de dato no se ajusta al definido", errorCode = 100)
  val exception3: SparkJobException = SparkJobException("error al transformar tipo String", errorCode = 101,
    cause = Some(new NumberFormatException("no se puede convertir String a Int")))

  def executeWithTryCatch(): Unit = {
    val value: String = "1000"
    try {
      value.toInt
    } catch {
      case exception: NumberFormatException => throw SparkJobException("error al transformar tipo String", errorCode = 101,
        cause = Some(exception))
    }
  }

  def executeWithEither(): Either[Throwable, Int] = {
    val value: String = "1000"
    if (value.length != value.filter(_.isDigit).length) {
      Left(throw SparkJobException("error al transformar tipo String", errorCode = 101))
    }
    else {
      Right(value.toInt)
    }
  }

  executeWithEither() match {
    case Right(value) => println(s"valor: $value")
    case Left(exception) => println(s"error: ${exception.getStackTrace.mkString("\n")}")
  }

  def executeWithTry(value: String): Try[Int] = Try(value.toInt)

  executeWithTry("1000") match {
    case Success(value) => value
    case Failure(exception) => println(s"error: ${exception.getStackTrace.mkString("\n")}")
  }

  val defaultURL: URL = new URL("http://www.marca.com")

  def parseUrl(url: String): Try[URL] = Try(new URL(url))

  parseUrl("http://www.bluetab.net").getOrElse(defaultURL)
  parseUrl("http://www.bluetab.net").toOption

  val protocol: Try[String] = parseUrl("xxxx//www.bluetab.net").map(_.getProtocol)
  // Failure(java.net.MalformedURLException: no protocol: xxxx)
  val port: Try[Int] = parseUrl("xxx://www.bluetab.net").map(_.getDefaultPort)
  // Success(80)

  def inputStreamForURLWithMap(url: String): Try[Try[Try[InputStream]]] = {
    parseUrl(url).map(u => Try(u.openConnection).map(connection => Try(connection.getInputStream)))
  }

  def inputStreamForURLWithFlatMap(url: String): Try[InputStream] = {
    parseUrl(url).flatMap(u => Try(u.openConnection).flatMap(connection => Try(connection.getInputStream)))
  }

  def inputStreamSyntacticSugar(url: String): Try[InputStream] = {
    for {
      url <- parseUrl(url)
      connection <- Try(url.openConnection)
      inputStream <- Try(connection.getInputStream)
    } yield inputStream
  }

  val value1: Option[Int] = allCatch.opt(1000) // Some(1000)
  val value2: Option[Int] = allCatch.opt(0 / 0) // None

  val value3: Either[Throwable, Int] = allCatch.either(1000) // Right(1000)
  val value4: Either[Throwable, Int] = allCatch.either(0 / 0) // Left(java.lang.ArithmeticException: / by zero)

  val value5: Try[Int] = allCatch.withTry(1000) // Success(1000)
  val value6: Try[Int] = allCatch.withTry(0 / 0) // Failure(java.lang.ArithmeticException: / by zero)

  val numberCatch: Catch[_] = failAsValue(classOf[NumberFormatException])(0)
  val jobCath: Catch[_] = catching(classOf[SparkJobException])
  val unknownException: Catch[_] = nonFatalCatch.withApply(exception => SparkJobException("error desconocido",
    errorCode = 200, cause = Some(exception)))

  def handlerException(catchList: Seq[Catch[_]]): Catch[_] = {
    catchList.reduce((x, y) => x.or(y))
  }

}
