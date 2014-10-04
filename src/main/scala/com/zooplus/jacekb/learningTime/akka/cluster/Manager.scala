package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope
import akka.routing.FromConfig
import com.zooplus.jacekb.learningTime.akka.pi.Commons.{Calculate, PiApproximation, Result, Work}
import com.zooplus.jacekb.learningTime.akka.pi.Worker

import scala.concurrent.duration._

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 10:02
 */
class Manager extends Actor with ActorLogging {

	val workerRouter = context.actorOf(FromConfig.props(Props[Worker]), name = "workerRouter")

	var pi: BigDecimal = 0
	var nrOfResults: Int = _
	var start: Long = System.currentTimeMillis
	var clusterClient: ActorRef = null
	val nrOfElements = 1000
	val nrOfMessages = 10

	Thread.sleep(10000)

	self ! Calculate

	def receive = {
		case Calculate ⇒
			log.info("Got Calculate message")
			start = System.currentTimeMillis
			clusterClient = sender()
			for (i ← 0 until nrOfMessages)
				workerRouter ! ConsistentHashableEnvelope(Work(i * nrOfElements, nrOfElements), i)
		case Result(value) ⇒
			log.info("Got result")
			pi += value
			nrOfResults += 1
			if (nrOfResults == nrOfMessages) {
				// Send the result to the listener
				clusterClient ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
				// Stops this actor and all its supervised children
				//context.stop(self)
			}
	}
}
