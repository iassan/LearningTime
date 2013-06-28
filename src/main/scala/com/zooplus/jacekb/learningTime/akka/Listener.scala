package com.zooplus.jacekb.learningTime.akka

import akka.actor.Actor
import com.zooplus.jacekb.learningTime.akka.Commons.PiApproximation

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:23
 */
class Listener extends Actor {
	def receive = {
		case PiApproximation(pi, duration) ⇒
			println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
				.format(pi, duration))
			context.system.shutdown()
	}
}
