package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{Props, ActorSystem}
import akka.cluster.Cluster
import com.zooplus.jacekb.learningTime.akka.common.Commons.{Calculate, Result}

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
		val manager = system.actorOf(Props[Manager], name = "manager")
		Cluster(system).subscribe(manager, classOf[Calculate])
		Cluster(system).subscribe(manager, classOf[Result])
	}
}
