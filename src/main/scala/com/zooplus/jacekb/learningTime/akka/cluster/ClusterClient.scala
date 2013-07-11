package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{Props, ActorSystem, Actor}
import com.zooplus.jacekb.learningTime.akka.common.Commons.{Calculate, PiApproximation}
import akka.cluster.Cluster

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 08:35
 */
object ClusterClient {

	def main(args: Array[String]) {
		// Create an Akka system
		val system = ActorSystem("ClusterSystem")

		val client = system.actorOf(Props[Client], name = "client")
		Cluster(system).subscribe(client, classOf[PiApproximation])
		val manager = system.actorOf(Props[Manager], name = "manager")
		println("Sending calculate message")
		manager ! new Calculate
	}

	class Client extends Actor {

		def receive = {
			case PiApproximation(pi, duration) ⇒
				println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s".format(pi, duration))
				context.system.shutdown()
		}
	}

}
