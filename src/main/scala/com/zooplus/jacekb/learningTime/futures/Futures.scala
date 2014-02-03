package com.zooplus.jacekb.learningTime.futures

import scala.concurrent._
import java.util.Date
import com.zooplus.jacekb.learningTime.akka.common.PiCalculator
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/01/14
 * Time: 08:59
 */
object Futures {

	def main(args: Array[String]): Unit = {
		val f: Future[(BigDecimal, Long)] = future {
			val piCalculator = new PiCalculator()
			val start = new Date().getTime
			val π = piCalculator.calculatePiFor(0, 500000000)
			val end = new Date().getTime
			(π, end - start)
		}
		println("Calculation of π fired up")
		f onComplete showResult
		Await.ready(f, Duration.Inf)
		Thread.sleep(1000)
	}

	def showResult(x: Try[(BigDecimal, Long)]): Unit = {
		x match {
			case Success(s) => println(s"Calculated π: ${s._1}, time: ${s._2}")
			case Failure(t) => println(s"Operation failed, exception message: ${t.getMessage}")
		}
	}
}
