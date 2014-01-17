package com.zooplus.jacekb.learningTime.errorHandling

import scala.util.{Failure, Success, Try}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 17/12/13
 * Time: 08:26
 */
object ErrorHandling {

	def mean(numbers: Seq[BigDecimal]): BigDecimal =
		numbers.sum / numbers.size

	def mean2(numbers: Seq[BigDecimal]): Option[BigDecimal] =
		if (numbers.size == 0)
			None
		else
			Some(numbers.sum / numbers.size)

	def mean3(numbers: Seq[BigDecimal]): Either[String, BigDecimal] =
		if (numbers.size == 0)
			Left("Sequence empty")
		else
			Right(numbers.sum / numbers.size)

	def mean4(numbers: Seq[BigDecimal]): Try[BigDecimal] =
		try {
			Success(numbers.sum / numbers.size)
		} catch {
			case e: Exception => Failure(e)
		}

	def three(x: BigDecimal): BigDecimal = x * 3

	def main(args: Array[String]): Unit = {
		println(mean4(Seq(1, 2, 3)))
		println(mean3(Seq(1, 2, 3)).right map three)
	}
}
