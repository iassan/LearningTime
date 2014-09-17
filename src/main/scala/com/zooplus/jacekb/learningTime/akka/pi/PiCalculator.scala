package com.zooplus.jacekb.learningTime.akka.pi

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 11/07/13
 * Time: 08:36
 */
class PiCalculator {

	def calculatePiFor(start: Int, nrOfElements: Int): BigDecimal = {
		var acc = 0.0
		for (i ← start until (start + nrOfElements))
			acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
		acc
	}
}
