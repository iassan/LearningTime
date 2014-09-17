package com.zooplus.jacekb.learningTime.akka.pi

import java.io.Serializable

import scala.concurrent.duration.Duration

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */
object Commons {

	sealed trait PiMessage extends Serializable

	case class Calculate() extends PiMessage

	case class Work(start: Int, nrOfElements: Int) extends PiMessage

	case class Result(value: BigDecimal) extends PiMessage

	case class PiApproximation(pi: BigDecimal, duration: Duration)

}
