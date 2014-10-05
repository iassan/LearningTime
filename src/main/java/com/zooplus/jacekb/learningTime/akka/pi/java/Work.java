package com.zooplus.jacekb.learningTime.akka.pi.java;

import java.io.Serializable;

/**
 * @author Jacek Bilski
 * @version $Revision$
 *          $Id$
 */
public class Work implements Serializable {

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
