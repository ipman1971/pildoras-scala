package net.bluetab.pildoras.scala.capsula3

import scala.annotation.tailrec

object Recursive {

  def sum(list: Seq[Int]): Int = {
    if(list.isEmpty) 0 else list.head + sum(list.tail)
  }

  def tailSum(list: Seq[Int], tailF: Seq[Int] => Int): Int = {
    if(list.isEmpty) 0 else list.head + tailF(list.tail)
  }

  def tailRecSum(list: Seq[Int]): Int = {
    sumWithAccumulator(list,0)
  }

  @tailrec
  private def sumWithAccumulator(list: Seq[Int], accumulator: Int): Int = list match {
      case Nil => accumulator
      case head :: tail => sumWithAccumulator(tail, head + accumulator)
  }

  def tailRecSumFunctionalStyle(list: Seq[Int]): Int = {
    @tailrec
    def sumWithAccumulator(list: Seq[Int], accumulator: Int): Int = list match {
      case Nil => accumulator
      case x :: xs => sumWithAccumulator(xs, x + accumulator)
    }
    sumWithAccumulator(list, 0)
  }

  def compute[T: Numeric](list: Seq[T], binaryFuntion: (T,T) => T, neutral: T): T = {
    @tailrec
    def applyFunction(list: Seq[T], accumulator: T): T = list match {
      case Nil => accumulator
      case x :: xs => applyFunction(xs, binaryFuntion(x,accumulator))
    }
    applyFunction(list,neutral)
  }

  def add(x: Int, y: Int): Int = x + y
  def product(x: Double, y: Double): Double = x * y

}
