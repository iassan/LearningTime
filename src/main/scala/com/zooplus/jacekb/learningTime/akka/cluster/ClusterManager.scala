package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{Props, ActorSystem}
import akka.contrib.pattern.ClusterReceptionistExtension

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:21
 */
object ClusterManager {
	def main(args: Array[String]) {
		System.setProperty("akka.remote.netty.tcp.port", "2553")
		val system = ActorSystem("PiClusterSystem")
		val manager = system.actorOf(Props[Manager], name = "manager")
		ClusterReceptionistExtension(system).registerService(manager)
	}
}
