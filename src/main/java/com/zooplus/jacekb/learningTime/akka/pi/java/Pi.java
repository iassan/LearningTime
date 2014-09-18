package com.zooplus.jacekb.learningTime.akka.pi.java;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.zooplus.jacekb.learningTime.akka.pi.Commons;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 17.09.14
 * Time: 09:37
 */
public class Pi {

    public static void main(String[] args) {
        Pi pi = new Pi();
        pi.calculate(8, 10000, 100000);
    }

    private void calculate(int nrOfWorkers, int nrOfElements, int nrOfMessages) {
        ActorSystem system = ActorSystem.create("PiSystem");
        ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
        ActorRef master = system.actorOf(Props.create(Master.class, nrOfWorkers, nrOfMessages, nrOfElements, listener), "master");
        master.tell(new Commons.Calculate(), ActorRef.noSender());
    }
}
