package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor._
import com.zooplus.jacekb.learningTime.akka.pi.Commons.{Calculate, PiApproximation}
import com.typesafe.config.ConfigFactory
import akka.contrib.pattern.ClusterClient

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 08:35
 */
object PiClusterClient {

	def main(args: Array[String]) {
		val config = ConfigFactory.load("clusterClient")
		val system = ActorSystem("PiClusterSystem", config)
		system.actorOf(Props[Client], name = "client")
	}

	class Client extends Actor with ActorLogging {

		val initialContacts = Set(context.actorSelection("akka.tcp://PiClusterSystem@localhost:2553/user/receptionist"))

		val clusterClient = context.actorOf(ClusterClient.props(initialContacts))

		clusterClient ! ClusterClient.Send("/user/manager", Calculate, localAffinity = true)

		def receive = {
			case PiApproximation(pi, duration) ⇒
				log.info("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s".format(pi, duration))
				context.system.shutdown()
		}
	}

}
