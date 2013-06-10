package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 13:00
 */
object Infix {
	def main(args: Array[String]) {
		println(calculate add 2 to 2) // 4
	}

	class MathOperations {
		var value: Int = 0

		def add(x: Int): MathOperations = {
			value = x
			this
		}

		def to(x: Int): Int = {
			value = value + x
			value
		}
	}

	def calculate: MathOperations = new MathOperations
}
