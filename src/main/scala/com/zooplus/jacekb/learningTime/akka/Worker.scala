package com.zooplus.jacekb.learningTime.akka

import akka.actor.Actor
import com.zooplus.jacekb.learningTime.akka.common.{PiCalculator, Commons}
import Commons.{Work, Result}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */

class Worker extends Actor {

	println("Creating worker")

	val piCalculator = new PiCalculator

	def receive = {
		case Work(start, nrOfElements) ⇒
			println("Got Work(" + start + ", " + nrOfElements + ")")
			sender ! Result(piCalculator.calculatePiFor(start, nrOfElements)) // perform the work
	}
}
