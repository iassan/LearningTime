package com.zooplus.jacekb.learningTime.akka

import akka.actor.ActorSystem

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 28/06/13
 * Time: 09:21
 */
object ClusterManager {
	def main(args: Array[String]) {
		val system = ActorSystem("ClusterSystem")
	}
}
