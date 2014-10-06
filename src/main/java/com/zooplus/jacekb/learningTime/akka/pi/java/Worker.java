package com.zooplus.jacekb.learningTime.akka.pi.java;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 09:58
 */
public class Worker extends AbstractActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	private PiCalculator piCalculator;

	public Worker() {
		receive(ReceiveBuilder.match(Work.class, this::calculate).build());
		piCalculator = new PiCalculator();
	}

	private void calculate(Work work) {
		log.info("Got Work(" + work.getStart() + ", " + work.getNrOfElements() + ")");
		BigDecimal value = piCalculator.calculatePiFor(work.getStart(), work.getNrOfElements());
		sender().tell(new Result(value), self());
	}
}
