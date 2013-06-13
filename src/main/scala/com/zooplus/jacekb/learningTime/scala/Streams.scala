package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 14:22
 */
object Streams {
	def main(args: Array[String]) {
		val s = fibbonaci // Stream(0, ?)
		println(s.take(10)) // Stream(0, ?)
		println(s.take(10).toList)
		// List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
		println(s.take(10))
		// Stream(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ?)
	}

	def fibbonaci: Stream[BigInt] = {
		def f(a: BigInt, b: BigInt): Stream[BigInt] = a #:: f(b, a + b)
		f(0, 1)
	}
}
