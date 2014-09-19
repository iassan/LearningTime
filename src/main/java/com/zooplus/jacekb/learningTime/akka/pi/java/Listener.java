package com.zooplus.jacekb.learningTime.akka.pi.java;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 14:56
 */
public class Listener extends AbstractActor {
    public Listener() {
        receive(ReceiveBuilder.match(PiApproximation.class, this::piApproximation).build());
    }

    private void piApproximation(PiApproximation piApproximation) {
        System.out.println("\n\tPi approximation: \t" + piApproximation.getPi() + "\n\tCalculation time: \t" + piApproximation.getDuration() + " ms");
        getContext().system().shutdown();
    }
}
