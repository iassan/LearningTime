package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class PiClusterClient {

	public static void main(String[] args) {
		Config config = ConfigFactory.load("clusterClient");
		ActorSystem system = ActorSystem.create("PiClusterSystem", config);
		system.actorOf(Props.create(ClusterClient.class), "piClusterClient");
	}
}
