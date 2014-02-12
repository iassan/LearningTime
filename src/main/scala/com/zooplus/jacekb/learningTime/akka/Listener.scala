package com.zooplus.jacekb.learningTime.akka

import akka.actor.Actor
import com.zooplus.jacekb.learningTime.akka.common.Commons
import Commons.PiApproximation

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:23
 */
class Listener extends Actor {
	def receive = {
		case PiApproximation(pi, duration) ⇒
			println(s"\n\tPi approximation: \t$pi\n\tCalculation time: \t$duration")
			context.system.shutdown()
	}
}
