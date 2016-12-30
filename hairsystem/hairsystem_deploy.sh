#!/bin/bash

mvn clean install

file="/opt/wildfly-10.1.0.Final/standalone/deployments/hairsystem.war.deployed"

if [ -f $file ] ; then
    rm $file
fi

cp /home/robson/Desktop/repositories/hairsystem/target/hairsystem.war /opt/wildfly-10.1.0.Final/standalone/deployments
