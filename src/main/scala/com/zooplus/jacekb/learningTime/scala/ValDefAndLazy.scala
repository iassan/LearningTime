package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 27/05/13
 * Time: 16:30
 */
object ValDefAndLazy {
	def main(args: Array[String]) {
		print("a")
		print(x)
		print(y)
		print(z)
		print(x)
		print(y)
		print(z)
		println("b")
	}

	val x = {
		print("x")
		1
	}

	def y = {
		print("y")
		2
	}

	lazy val z = {
		print("z")
		3
	}
}
