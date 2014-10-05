package com.zooplus.jacekb.learningTime.akka.pi.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ConsistentHashingRouter;
import akka.routing.FromConfig;
import com.zooplus.jacekb.learningTime.akka.pi.java.Calculate;
import com.zooplus.jacekb.learningTime.akka.pi.java.PiApproximation;
import com.zooplus.jacekb.learningTime.akka.pi.java.Result;
import com.zooplus.jacekb.learningTime.akka.pi.java.Work;
import com.zooplus.jacekb.learningTime.akka.pi.java.Worker;

import java.math.BigDecimal;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class ClusterManager extends AbstractActor {
	private int nrOfMessages;
	private int nrOfElements;
	private ActorRef listener;
	private BigDecimal π = BigDecimal.ZERO;
	private int nrOfResults = 0;
	private long start;
	private ActorRef workerRouter;

	public ClusterManager(int nrOfMessages, int nrOfElements) {
		this.nrOfMessages = nrOfMessages;
		this.nrOfElements = nrOfElements;
		workerRouter = getContext().actorOf(FromConfig.getInstance().props(Props.create(Worker.class)), "workerRouter");
		receive(ReceiveBuilder.
				match(Calculate.class, this::calculate).
				match(Result.class, this::result).
				build());
	}

	private void result(Result result) {
		π = π.add(result.getValue());
		nrOfResults++;
		if (nrOfResults == nrOfMessages) {
			Long duration = System.currentTimeMillis() - start;
			listener.tell(new PiApproximation(π, duration), self());
			nrOfResults = 0;
			π = BigDecimal.ZERO;
		}
	}

	private void calculate(Calculate calculate) {
		start = System.currentTimeMillis();
		for (int i = 0; i < nrOfMessages; i++) {
			workerRouter.tell(new ConsistentHashingRouter.ConsistentHashableEnvelope(new Work(i * nrOfElements, nrOfElements), i), self());
		}
		listener = sender();    // new in cluster
	}
}
