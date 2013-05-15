package com.zooplus.jacekb.learningTime.akka

import akka.actor._
import akka.routing.RoundRobinRouter
import scala.concurrent.duration._
import com.zooplus.jacekb.learningTime.akka.Commons.{PiApproximation, Result, Work, Calculate}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 29/04/13
 * Time: 08:50
 */
object Pi extends App {

	calculate(nrOfWorkers = 16, nrOfElements = 10000, nrOfMessages = 100000)

	class Master(nrOfWorkers: Int,
							 nrOfMessages: Int,
							 nrOfElements: Int,
							 listener: ActorRef) extends Actor {

		var pi: BigDecimal = 0
		var nrOfResults: Int = _
		val start: Long = System.currentTimeMillis

		val workerAddresses = Seq(AddressFromURIString("akka://PiSystem@192.168.22.60:1235"))

		val workerRouter = context.actorOf(
			Props[Worker].withRouter(RoundRobinRouter(nrOfWorkers)), name = "workerRouter")

		def receive = {
			case Calculate ⇒
				for (i ← 0 until nrOfMessages)
					workerRouter ! Work(i * nrOfElements, nrOfElements)
			case Result(value) ⇒
				pi += value
				nrOfResults += 1
				if (nrOfResults == nrOfMessages) {
					// Send the result to the listener
					listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
					// Stops this actor and all its supervised children
					context.stop(self)
				}
		}
	}

	class Listener extends Actor {
		def receive = {
			case PiApproximation(pi, duration) ⇒
				println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
					.format(pi, duration))
				context.system.shutdown()
		}
	}

	def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
		// Create an Akka system
		val system = ActorSystem("PiSystem")

		// create the result listener, which will print the result and
		// shutdown the system
		val listener = system.actorOf(Props[Listener], name = "listener")

		// create the master
		val master = system.actorOf(Props(new Master(
			nrOfWorkers, nrOfMessages, nrOfElements, listener)),
			name = "master")

		// start the calculation
		master ! Calculate
	}
}
