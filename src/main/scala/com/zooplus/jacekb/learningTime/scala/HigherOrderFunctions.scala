package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 27/05/13
 * Time: 16:02
 */
object HigherOrderFunctions {
	def main(args: Array[String]) {
		println(f(3)) // 6
	}

	def f(x: Int) = {
		// legal, function is an object
		def h(x: Int) = 2 * x

		// 0-parameter function returning function
		def u: Int => Int = h

		// passing function to function
		// u is at first evaluated into another function
		g(x, u)
	}

	// t is a function getting an Int and returning an Int
	def g(x: Int, t: Int => Int) = t(x)
}
