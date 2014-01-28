package com.zooplus.jacekb.learningTime.futures

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/01/14
 * Time: 09:06
 */
object Promises {

	def main(args: Array[String]): Unit = {
		// one way pipe, "in" end
		val p: Promise[String] = Promise[String]()
		// "out" end
		val f: Future[String] = p.future
		// when Future completes run given function
		f onComplete showResult

		println("Trying to fulfill a promise")
		p.success("Promise fulfilled")
		Thread.sleep(1000)
	}

	def showResult(x: Try[String]): Unit = {
		x match {
			case Success(s) => println(s)
			case Failure(t) => println(s"Operation failed, exception message: ${t.getMessage}")
		}
	}
}
