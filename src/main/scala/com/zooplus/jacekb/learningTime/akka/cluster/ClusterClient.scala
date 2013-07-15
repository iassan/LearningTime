package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{ActorLogging, Props, ActorSystem, Actor}
import com.zooplus.jacekb.learningTime.akka.common.Commons.{Calculate, PiApproximation}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{ClusterDomainEvent, UnreachableMember, MemberUp, CurrentClusterState}

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

		val clusterListener = system.actorOf(Props(new Actor with ActorLogging {
			def receive = {
				case state: CurrentClusterState ⇒
					log.info("Current members: {}", state.members)
				case MemberUp(member) ⇒
					log.info("Member is Up: {}", member)
				case UnreachableMember(member) ⇒
					log.info("Member detected as unreachable: {}", member)
				case _: ClusterDomainEvent ⇒ // ignore
			}
		}), name = "clusterListener")
		val cluster = Cluster(system)
		cluster.subscribe(clusterListener, classOf[ClusterDomainEvent])
		val client = system.actorOf(Props[Client], name = "client")
		cluster.subscribe(client, classOf[PiApproximation])
		val manager = system.actorOf(Props[Manager], name = "manager")
		Thread.sleep(7000)
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
