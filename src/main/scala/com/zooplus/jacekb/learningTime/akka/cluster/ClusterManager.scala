package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{ActorLogging, Actor, Props, ActorSystem}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{ClusterDomainEvent, UnreachableMember, MemberUp, CurrentClusterState}
import com.zooplus.jacekb.learningTime.akka.pi.Commons.Calculate

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:21
 */
object ClusterManager {
	def main(args: Array[String]) {
		System.setProperty("akka.remote.netty.tcp.port", "2553")
		val system = ActorSystem("ClusterSystem")
//		val clusterListener = system.actorOf(Props(new Actor with ActorLogging {
//			def receive = {
//				case state: CurrentClusterState ⇒
//					log.info("Current members: {}", state.members)
//				case MemberUp(member) ⇒
//					log.info("Member is Up: {}", member)
//				case UnreachableMember(member) ⇒
//					log.info("Member detected as unreachable: {}", member)
//				case _: ClusterDomainEvent ⇒ // ignore
//			}
//		}), name = "clusterListener")
//		val cluster = Cluster(system)
//		cluster.subscribe(clusterListener, classOf[ClusterDomainEvent])
		val manager = system.actorOf(Props[Manager], name = "manager")
//		Thread.sleep(10000)
//		manager ! Calculate
	}
}
