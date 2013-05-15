package com.zooplus.jacekb.learningTime.akka

import org.scalatest.junit.JUnitSuite
import org.junit.{Ignore, Test}

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 29/04/13
 * Time: 09:31
 */
class PiTest extends JUnitSuite {

	@Test
	def testPiCalculation() {
		Pi.calculate(nrOfWorkers = 4, nrOfElements = 10000, nrOfMessages = 10000)
	}
}
