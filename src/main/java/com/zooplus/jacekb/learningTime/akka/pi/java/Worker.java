package com.zooplus.jacekb.learningTime.akka.pi.java;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 09:58
 */
public class Worker extends AbstractActor {

    private PiCalculator piCalculator;

    public Worker() {
        receive(ReceiveBuilder.match(Work.class, this::calculate).build());
        piCalculator = new PiCalculator();
    }

    private void calculate(Work work) {
        sender().tell(new Result(piCalculator.calculatePiFor(work.getStart(), work.getNrOfElements())), self());
    }
}
