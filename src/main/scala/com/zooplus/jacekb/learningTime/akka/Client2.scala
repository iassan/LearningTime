package com.zooplus.jacekb.learningTime.akka

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:29
 */
object Client2 extends App {

	val system = ActorSystem("PiSystem", ConfigFactory.load.getConfig("client2"))
}
