package com.zooplus.jacekb.learningTime.akka

import akka.actor.Actor
import com.zooplus.jacekb.learningTime.akka.Commons.{Work, Result}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */

class Worker extends Actor {

	println("Creating worker")

	def calculatePiFor(start: Int, nrOfElements: Int): BigDecimal = {
		var acc = 0.0
		for (i ← start until (start + nrOfElements))
			acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
		acc
	}

	def receive = {
		case Work(start, nrOfElements) ⇒
			sender ! Result(calculatePiFor(start, nrOfElements)) // perform the work
	}
}
