package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 14:49
 */
object Tuples {
	def main(args: Array[String]) {
		val x = f(1, 2)
		println(x) // (1,2)
		println(x._1) // 1
		println(x._2) // 2
	}

	def f(x: Int, y: Int): (Int, Int) = (x, y)
}
