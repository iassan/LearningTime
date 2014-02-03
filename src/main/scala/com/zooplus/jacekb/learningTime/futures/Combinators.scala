package com.zooplus.jacekb.learningTime.futures

import scala.concurrent._
import java.util.Date
import com.zooplus.jacekb.learningTime.akka.common.PiCalculator
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import java.net.{InetSocketAddress, URL, Proxy}
import scala.io.Source
import scala.async.Async.async

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
			try {
				val start = new Date().getTime
				val url = new URL("http://www.angio.net/pi/digits/pi1000000.txt")
				//println(s"URL: $url")
				val proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.20.55", 3128))
				//println(s"Proxy: $proxy")
				val in = Source.fromInputStream(url.openConnection(proxy).getInputStream)
				//val in = Source.fromInputStream(url.openStream)
				//println(s"Got stream")
				val data = in.getLines().next().substring(0, 20)
				//println(s"Got data: $data")
				val end = new Date().getTime
				(BigDecimal(data), end - start)
			} catch {
				case t: Throwable =>
					println(s"Throwable during download: ${t.getMessage}")
					throw new RuntimeException()
			}
		}
		println("Downloading of π fired up")
		d.map(x => println(s"Downloaded π: ${x._1}, time: ${x._2}"))
		val both = Future.all(List(f, d))
		both map compareπ(globalStart)
		Await.ready(both, Duration.Inf)
		Thread.sleep(1000)
	}

	def showResult(x: (BigDecimal, Long)): Unit = {
		println(s"Calculated π: ${x._1}, time: ${x._2}")
	}

	def compareπ(globalStart: Long)(x: List[(BigDecimal, Long)]): Unit = {
		val a = x(0)._1
		val b = x(1)._1
		val globalEnd = new Date().getTime
		println(s"Difference in π values: ${(a - b).abs.bigDecimal.toPlainString}")
		println(s"Global execution time: ${globalEnd - globalStart}")
	}

	implicit class FutureCompanionOps[T](val f: Future.type) extends AnyVal {
		def all[T](fs: List[Future[T]]): Future[List[T]] = {
			async {
				fs.map(Await.result(_, Duration.Inf))
			}
		}
	}

}
