package com.zooplus.jacekb.learningTime.scala

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 27/05/13
 * Time: 15:34
 */
object PatternMatching {
	def main(args: Array[String]) {
		f(A("abc")) // "Got string: abc"
		f(B(3)) // "Got int: 3"
		f(1.5) // Exception in thread "main"
		// scala.MatchError: 1.5 (of class java.lang.Double)
	}

	case class A(s: String)

	case class B(i: Int)

	def f(x: Any) {
		// Any is the root of the Scala class hierarchy
		x match {
			case A(s) => println("Got string: " + s)
			case B(i) => println("Got int: " + i)
		}
	}
}
