package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 10/06/13
 * Time: 13:34
 */
object Traits {
	def main(args: Array[String]) {
		C.a()
		C.b()
	}

	trait A {
		def a() {
			println("a")
		}
	}

	trait B {
		def b() {
			println("b")
		}
	}

	object C extends A with B

}
