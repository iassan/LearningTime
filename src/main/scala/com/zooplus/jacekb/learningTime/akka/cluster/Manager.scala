package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{ActorRef, Props, Actor}
import scala.concurrent.duration._
import com.zooplus.jacekb.learningTime.akka.common.Commons.{PiApproximation, Result, Work, Calculate}
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.Cluster
import akka.routing.FromConfig

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 10:02
 */
class Manager extends Actor {
	val workerRouter = context.actorOf(Props.empty.withRouter(FromConfig), name = "workerRouter")

	var pi: BigDecimal = 0
	var nrOfResults: Int = _
	var start: Long = System.currentTimeMillis
	var clusterClient: ActorRef = null
	val nrOfElements = 1000000
	val nrOfMessages = 10000

	val cluster = Cluster(context.system)

	// subscribe to cluster changes, MemberUp
	// re-subscribe when restart
	override def preStart() {
		cluster.subscribe(self, classOf[MemberUp])
		cluster.subscribe(self, classOf[Calculate])
		cluster.subscribe(self, classOf[Result])
	}

	override def postStop() {
		cluster.unsubscribe(self)
	}

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
