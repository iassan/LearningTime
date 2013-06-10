package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 14:21
 */
object CollectionOperations {
	def main(args: Array[String]) {
		println(Streams.fibbonaci.map(3 * _).
			filter(_ % 2 != 0).take(10).toList)
		// List(3, 3, 9, 15, 39, 63, 165, 267, 699, 1131)
		println(Streams.fibbonaci.map(m).filter(f).take(10).toList)
		// List(3, 3, 9, 15, 39, 63, 165, 267, 699, 1131)
		println(Streams.fibbonaci.take(10).foldLeft(BigInt(0))(_ + _))
		// 88
	}

	def m(x: BigInt) = 3 * x

	def f(x: BigInt) = x % 2 != 0
}
