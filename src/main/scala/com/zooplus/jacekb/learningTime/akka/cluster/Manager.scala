package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
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
	val workerRouter = context.actorOf(Props[Worker].withRouter(FromConfig), name = "workerRouter")

	val role = "manager"

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
		log.info("Manager subscribing to messages")
		cluster.subscribe(self, classOf[MemberUp])
		cluster.subscribe(self, classOf[Calculate])
		cluster.subscribe(self, classOf[Result])
	}

	override def postStop() {
		cluster.unsubscribe(self)
	}

	def receive = {
		case Calculate ⇒
			log.info("Got Calculate message")
			start = System.currentTimeMillis
			clusterClient = sender
			for (i ← 0 until nrOfMessages)
				workerRouter ! ConsistentHashableEnvelope(Work(i * nrOfElements, nrOfElements), "pi")
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
