package com.zooplus.jacekb.learningTime.akka

import akka.cluster.ClusterEvent._
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.ClusterEvent.UnreachableMember
import akka.cluster.ClusterEvent.MemberJoined
import akka.actor.{ActorSystem, Props, Actor, ActorLogging}
import com.zooplus.jacekb.learningTime.akka.Commons.Work

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:04
 */
object ClusterWorker {
	def main(args: Array[String]) {

		// Override the configuration of the port
		// when specified as program argument
		if (args.nonEmpty) System.setProperty("akka.remote.netty.port", args(0))

		// Create an Akka system
		val system = ActorSystem("ClusterSystem")
		val clusterListener = system.actorOf(Props(new Actor with ActorLogging {
			def receive = {
				case state: CurrentClusterState ⇒
					log.info("Current members: {}", state.members)
				case MemberJoined(member) ⇒
					log.info("Member joined: {}", member)
				case MemberUp(member) ⇒
					log.info("Member is Up: {}", member)
				case UnreachableMember(member) ⇒
					log.info("Member detected as unreachable: {}", member)
				case _: ClusterDomainEvent ⇒ // ignore

			}
		}), name = "clusterListener")

		val cluster = Cluster(system)
		cluster.subscribe(clusterListener, classOf[ClusterDomainEvent])
		val worker = new Worker
		cluster.subscribe(worker.self, classOf[Work])
	}
}
