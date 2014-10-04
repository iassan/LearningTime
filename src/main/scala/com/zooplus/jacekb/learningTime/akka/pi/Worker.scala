package com.zooplus.jacekb.learningTime.akka.pi

import akka.actor.{Actor, ActorLogging}
import com.zooplus.jacekb.learningTime.akka.pi.Commons.{Result, Work}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */

class Worker extends Actor with ActorLogging {

	val piCalculator = new PiCalculator

	def receive = {
		case Work(start, nrOfElements) ⇒
			log.info("Got Work(" + start + ", " + nrOfElements + ")")
			sender ! Result(piCalculator.calculatePiFor(start, nrOfElements)) // perform the work
	}
}
