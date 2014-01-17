package com.zooplus.jacekb.learningTime.akka

import akka.actor.{ActorLogging, Actor}
import com.zooplus.jacekb.learningTime.akka.common.{PiCalculator, Commons}
import Commons.{Work, Result}

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

	override def preStart() {
		log.info("Worker starting")
	}

	def receive = {
		case Work(start, nrOfElements) ⇒
			//log.info("Got Work(" + start + ", " + nrOfElements + ")")
		sender ! Result(piCalculator.calculatePiFor(start, nrOfElements)) // perform the work
	}
}
