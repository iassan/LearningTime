package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.contrib.pattern.ClusterReceptionistExtension;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class RunManagerInCluster {

	public static void main(String[] args) {
		System.setProperty("akka.remote.netty.tcp.port", "2553");
		ActorSystem system = ActorSystem.create("PiClusterSystem");
		Integer nrOfMessages = 10;
		Integer nrOfElements = 1000;
		ActorRef manager = system.actorOf(Props.create(ClusterManager.class, nrOfMessages, nrOfElements), "manager");
		ClusterReceptionistExtension.get(system).registerService(manager);
	}
}
