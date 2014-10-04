#!/bin/bash
SCALA_VERSION=2.11.2
SCALA_MAJOR_VERSION=2.11
AKKA_VERSION=2.3.6
TYPESAFE_CONFIG_VERSION=1.2.1
GOOGLE_PROTOBUF_VERSION=2.5.0
java -cp target/jacekb.learningTime-1.0-SNAPSHOT.jar:/home/$USER/.m2/repository/org/scala-lang/scala-library/$SCALA_VERSION/scala-library-$SCALA_VERSION.jar com.zooplus.jacekb.learningTime.errorHandling.ErrorHandling
