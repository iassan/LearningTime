package com.zooplus.jacekb.learningTime.akka.pi.java;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class Work {

	private int start;

	private int nrOfElements;

	public Work(int start, int nrOfElements) {
		this.start = start;
		this.nrOfElements = nrOfElements;
	}

	public int getStart() {
		return start;
	}

	public int getNrOfElements() {
		return nrOfElements;
	}
}
