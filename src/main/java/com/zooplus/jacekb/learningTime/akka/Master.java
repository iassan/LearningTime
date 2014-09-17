package com.zooplus.jacekb.learningTime.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import com.zooplus.jacekb.learningTime.akka.pi.Commons;
import com.zooplus.jacekb.learningTime.akka.pi.Worker;
import scala.concurrent.duration.Duration;
import scala.math.BigDecimal;

import java.util.concurrent.TimeUnit;

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
    private BigDecimal pi = BigDecimal.valueOf(0l);
    private int nrOfResults = 0;
    private long start = System.currentTimeMillis();
    private ActorRef workerRouter;

    public Master(int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;
        workerRouter = getContext().actorOf(new RoundRobinPool(nrOfWorkers).props(Props.create(Worker.class)), "workerRouter");
        receive(ReceiveBuilder.
                match(Commons.Calculate.class, this::calculate).
                match(Commons.Result.class, this::result).
                build());
    }

    private void result(Commons.Result result) {
        pi = pi.$plus(result.value());
        nrOfResults++;
        if (nrOfResults == nrOfMessages) {
            Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
            listener.tell(new Commons.PiApproximation(pi, duration), self());
            getContext().stop(self());
        }
    }

    private void calculate(Commons.Calculate calculate) {
        for (int i = 0; i < nrOfMessages; i++) {
            workerRouter.tell(new Commons.Work(i * nrOfElements, nrOfElements), self());
        }
    }
}
