package com.zooplus.jacekb.learningTime.akka.pi.java;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 17.09.14
 * Time: 09:38
 */
public class Master extends AbstractActor {
    private int nrOfMessages;
    private int nrOfElements;
    private ActorRef listener;
    private BigDecimal π = BigDecimal.ZERO;
    private int nrOfResults = 0;
    private long start = System.currentTimeMillis();
    private ActorRef workerRouter;

    public Master(int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;
        workerRouter = getContext().actorOf(new RoundRobinPool(nrOfWorkers).props(Props.create(Worker.class)), "workerRouter");
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
            getContext().stop(self());
        }
    }

    private void calculate(Calculate calculate) {
        for (int i = 0; i < nrOfMessages; i++) {
            workerRouter.tell(new Work(i * nrOfElements, nrOfElements), self());
        }
    }
}
