package com.zooplus.jacekb.learningTime.akka.pi

import akka.actor.{Actor, ActorLogging}
import com.zooplus.jacekb.learningTime.akka.pi.Commons.{Result, Work}
import akka.cluster.Cluster

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */

class Worker extends Actor with ActorLogging {

	println("Creating worker")

	val role = "worker"

	val piCalculator = new PiCalculator
	val cluster = Cluster(context.system)

	override def preStart() {
		log.info("Worker starting")
		cluster.subscribe(self, classOf[Work])
	}

	override def postStop() {
		cluster.unsubscribe(self)
	}

	def receive = {
		case Work(start, nrOfElements) ⇒
			//log.info("Got Work(" + start + ", " + nrOfElements + ")")
			sender ! Result(piCalculator.calculatePiFor(start, nrOfElements)) // perform the work
	}
}
