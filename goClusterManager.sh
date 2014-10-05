#!/bin/bash
SCALA_VERSION=2.11.2
SCALA_MAJOR_VERSION=2.11
AKKA_VERSION=2.3.6
TYPESAFE_CONFIG_VERSION=1.2.1
GOOGLE_PROTOBUF_VERSION=2.5.0
java -cp target/jacekb.learningTime-1.0-SNAPSHOT.jar:/home/$USER/.m2/repository/org/scala-lang/scala-library/$SCALA_VERSION/scala-library-$SCALA_VERSION.jar:/home/$USER/.m2/repository/com/typesafe/akka/akka-actor_$SCALA_MAJOR_VERSION/$AKKA_VERSION/akka-actor_$SCALA_MAJOR_VERSION-$AKKA_VERSION.jar:/home/$USER/.m2/repository/com/typesafe/config/$TYPESAFE_CONFIG_VERSION/config-$TYPESAFE_CONFIG_VERSION.jar:/home/$USER/.m2/repository/com/typesafe/akka/akka-remote_$SCALA_MAJOR_VERSION/$AKKA_VERSION/akka-remote_$SCALA_MAJOR_VERSION-$AKKA_VERSION.jar:/home/$USER/.m2/repository/com/typesafe/akka/akka-cluster_$SCALA_MAJOR_VERSION/$AKKA_VERSION/akka-cluster_$SCALA_MAJOR_VERSION-$AKKA_VERSION.jar:/home/$USER/.m2/repository/com/typesafe/akka/akka-contrib_$SCALA_MAJOR_VERSION/$AKKA_VERSION/akka-contrib_$SCALA_MAJOR_VERSION-$AKKA_VERSION.jar:/home/$USER/.m2/repository/com/google/protobuf/protobuf-java/$GOOGLE_PROTOBUF_VERSION/protobuf-java-$GOOGLE_PROTOBUF_VERSION.jar:/home/$USER/.m2/repository/io/netty/netty/3.5.8.Final/netty-3.5.8.Final.jar:/home/$USER/.m2/repository/net/sandrogrzicic/scalabuff-runtime_$SCALA_MAJOR_VERSION/1.2.0/scalabuff-runtime_$SCALA_MAJOR_VERSION-1.2.0.jar com.zooplus.jacekb.learningTime.akka.cluster.ClusterManager $1
