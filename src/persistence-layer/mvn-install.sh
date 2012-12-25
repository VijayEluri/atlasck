#!/bin/bash

group=com.atlasck
artifact=persistence-layer
version=0.1.0.BUILD-SNAPSHOT
finalName=${artifact}-${version}.jar

mvn clean package -DskipTests=true
mvn install:install-file -Dfile=target/$finalName -DgroupId=$group \
	-DartifactId=$artifact -Dversion=$version -Dpackaging=jar
