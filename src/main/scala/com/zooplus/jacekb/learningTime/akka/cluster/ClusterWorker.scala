package com.zooplus.jacekb.learningTime.akka.cluster

import akka.actor.{ActorSystem, Props}
import com.zooplus.jacekb.learningTime.akka.pi.Worker

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:04
 */
object ClusterWorker {

	def main(args: Array[String]) {
		val system = ActorSystem("ClusterSystem")
		system.actorOf(Props[Worker], name = "worker")
	}
}
