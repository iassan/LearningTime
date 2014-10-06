package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.*;
import akka.contrib.pattern.ClusterClient;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zooplus.jacekb.learningTime.akka.pi.java.Calculate;
import com.zooplus.jacekb.learningTime.akka.pi.java.PiApproximation;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class PiClusterClient extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public static void main(String[] args) {
		Config config = ConfigFactory.load("clusterClient");
		ActorSystem system = ActorSystem.create("PiClusterSystem", config);
		system.actorOf(Props.create(PiClusterClient.class), "piClusterClient");
	}

    public PiClusterClient() {
        Set<ActorSelection> initialContacts = new HashSet<>();
        initialContacts.add(getContext().actorSelection("akka.tcp://PiClusterSystem@localhost:2553/user/receptionist"));
        ActorRef clusterClient = getContext().actorOf(ClusterClient.defaultProps(initialContacts));
        clusterClient.tell(new ClusterClient.Send("/user/manager", new Calculate()), self());
        receive(ReceiveBuilder.
                match(PiApproximation.class, this::processResult).
                build());
    }

    private void processResult(PiApproximation piApproximation) {
        log.info("\n\tPi approximation: \t"+piApproximation.getPi()+"\n\tCalculation time: \t"+piApproximation.getDuration());
        getContext().system().shutdown();
    }
}
