package com.zooplus.jacekb.learningTime.akka.pi

import akka.actor._
import com.zooplus.jacekb.learningTime.akka.Master
import com.zooplus.jacekb.learningTime.akka.pi.Commons.Calculate

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 29/04/13
 * Time: 08:50
 */
object Pi extends App {

	calculate(nrOfWorkers = 8, nrOfElements = 10000, nrOfMessages = 100000)

	//	class Master(nrOfWorkers: Int,
	//							 nrOfMessages: Int,
	//							 nrOfElements: Int,
	//							 listener: ActorRef) extends Actor {
	//
	//		var pi: BigDecimal = 0
	//		var nrOfResults: Int = _
	//		val start: Long = System.currentTimeMillis
	//
	//		val workerRouter = context.actorOf(
	//			Props[Worker].withRouter(RoundRobinRouter(nrOfWorkers)), name = "workerRouter")
	//
	//		def receive = {
	//			case Calculate ⇒
	//				for (i ← 0 until nrOfMessages)
	//					workerRouter ! Work(i * nrOfElements, nrOfElements)
	//			case Result(value) ⇒
	//				pi += value
	//				nrOfResults += 1
	//				if (nrOfResults == nrOfMessages) {
	//					// Send the result to the listener
	//					listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
	//					// Stops this actor and all its supervised children
	//					context.stop(self)
	//				}
	//		}
	//	}

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
		master.tell(new Calculate, ActorRef.noSender)
	}
}
