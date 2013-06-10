package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 16:40
 */
object Closures {
	def main(args: Array[String]) {
		lazy val x = {
			// type: Unit
			print("x")
		}
		f(x) // ax
	}

	def f(body: => Unit) {
		print("a")
		body
	}
}
