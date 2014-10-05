package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.zooplus.jacekb.learningTime.akka.pi.java.Calculate;
import com.zooplus.jacekb.learningTime.akka.pi.java.PiApproximation;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class ClusterClient extends AbstractActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public ClusterClient() {
		Set<ActorSelection> initialContacts = new HashSet<>();
		initialContacts.add(getContext().actorSelection("akka.tcp://PiClusterSystem@localhost:2553/user/receptionist"));
		ActorRef clusterClient = getContext().actorOf(akka.contrib.pattern.ClusterClient.defaultProps(initialContacts));
		clusterClient.tell(new akka.contrib.pattern.ClusterClient.Send("/user/manager", new Calculate(), true), self());
		receive(ReceiveBuilder.
				match(PiApproximation.class, this::processResult).
				build());
	}

	private void processResult(PiApproximation piApproximation) {
		log.info("\n\tPi approximation: \t"+piApproximation.getPi()+"\n\tCalculation time: \t"+piApproximation.getDuration());
		getContext().system().shutdown();
	}
}
