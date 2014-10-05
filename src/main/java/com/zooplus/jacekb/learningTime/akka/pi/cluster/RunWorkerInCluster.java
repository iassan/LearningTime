package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.zooplus.jacekb.learningTime.akka.pi.java.Worker;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class RunWorkerInCluster {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("PiClusterSystem");
		system.actorOf(Props.create(Worker.class), "worker");
	}
}
