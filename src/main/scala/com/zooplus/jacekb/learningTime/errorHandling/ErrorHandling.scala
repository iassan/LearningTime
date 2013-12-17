package com.zooplus.jacekb.learningTime.errorHandling

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

	def three(x: BigDecimal): BigDecimal = x * 3

	def main(args: Array[String]): Unit = {
		println(mean(Seq(1, 2, 3)))
	}
}
