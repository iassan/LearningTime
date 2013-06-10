package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 27/05/13
 * Time: 16:13
 */
object TailRecursion {
	def main(args: Array[String]) {
		println("g: " + g(1000000000)) // "g: 1000000000"
		println("f: " + f(1000000000)) // StackOverflowError
	}

	def f(x: Int): Int = {
		if (x > 0) {
			// return call to itself plus one additional operation
			1 + f(x - 1)
		} else {
			0
		}
	}

	def g(x: Int): Int = {
		def t(x: Int, acc: Int): Int = {
			if (x > 0) {
				// return call to itself without any other operations
				t(x - 1, acc + 1)
			} else {
				acc
			}
		}
		t(x, 0)
	}
}
