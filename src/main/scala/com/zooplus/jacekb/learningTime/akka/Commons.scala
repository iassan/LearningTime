package com.zooplus.jacekb.learningTime.akka

import scala.concurrent.duration.Duration
import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 15/05/13
 * Time: 08:21
 */
object Commons {

	sealed trait PiMessage extends Serializable

	case object Calculate extends PiMessage

	case class Work(start: Int, nrOfElements: Int) extends PiMessage

	case class Result(value: BigDecimal) extends PiMessage

	case class PiApproximation(pi: BigDecimal, duration: Duration)
}
