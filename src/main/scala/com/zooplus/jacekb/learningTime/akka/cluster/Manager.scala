package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{ActorRef, Props, Actor}
import scala.concurrent.duration._
import com.zooplus.jacekb.learningTime.akka.common.Commons.{PiApproximation, Result, Work, Calculate}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 10:02
 */
class Manager extends Actor {
	val workerRouter = context.actorOf(Props.empty, name = "workerRouter")

	var pi: BigDecimal = 0
	var nrOfResults: Int = _
	var start: Long = System.currentTimeMillis
	var clusterClient: ActorRef = null
	val nrOfElements = 1000000
	val nrOfMessages = 10000

	def receive = {
		case Calculate ⇒
			println("Got Calculate message")
			start = System.currentTimeMillis
			clusterClient = sender
			for (i ← 0 until nrOfMessages)
				workerRouter ! Work(i * nrOfElements, nrOfElements)
		case Result(value) ⇒
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
