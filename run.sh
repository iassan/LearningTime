#!/bin/bash

java -cp target/jacekb.learningTime-1.0-SNAPSHOT.jar:/home/$USER/.m2/repository/org/scala-lang/scala-library/2.10.1/scala-library-2.10.1.jar com.zooplus.jacekb.learningTime.scala.$1
