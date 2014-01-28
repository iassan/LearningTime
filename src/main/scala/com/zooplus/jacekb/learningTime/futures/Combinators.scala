package com.zooplus.jacekb.learningTime.futures

import scala.concurrent._
import java.util.Date
import com.zooplus.jacekb.learningTime.akka.common.PiCalculator
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import java.net.URL
import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/01/14
 * Time: 09:38
 */
object Combinators {

	def main(args: Array[String]): Unit = {
		val globalStart = new Date().getTime
		val f: Future[(BigDecimal, Long)] = future {
			val piCalculator = new PiCalculator()
			val start = new Date().getTime
			val π = piCalculator.calculatePiFor(0, 500000000)
			val end = new Date().getTime
			(π, end - start)
		}
		println("Calculation of π fired up")
		f map showResult
		val d: Future[(BigDecimal, Long)] = future {
			val start = new Date().getTime
			val url = new URL("http://www.angio.net/pi/digits/50.txt")
			val in = Source.fromInputStream(url.openStream())
			val data = in.getLines().next()
			val end = new Date().getTime
			(BigDecimal(data), end - start)
		}
		println("Downloading of π fired up")
		d.map(x => println(s"Downloaded π: ${x._1}, time: ${x._2}"))
		Future.all(List(f, d)) map compareπ(globalStart)
		Thread.sleep(10000)
	}

	def showResult(x: (BigDecimal, Long)): Unit = {
		println(s"Calculated π: ${x._1}, time: ${x._2}")
	}

	def compareπ(globalStart: Long)(x: List[(BigDecimal, Long)]): Unit = {
		val a = x(0)._1
		val b = x(1)._1
		val globalEnd = new Date().getTime
		println(s"Difference in π values: ${(a - b).abs}")
		println(s"Global execution time: ${globalEnd - globalStart}")
	}

	implicit class FutureCompanionOps[T](val f: Future.type) extends AnyVal {
		def always[T](value: T): Future[T] = Promise.successful(value).future

		def all[T](fs: List[Future[T]]): Future[List[T]] = always(blocking {
			fs.map(Await.result(_, Duration.Inf))
		})
	}

}
