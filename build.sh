#!/bin/bash

cd  userservice/
source ./env-variable.sh
mvn clean package
docker build -t user-app1 .
cd ..
cd  favouriteservice/
source ./env-variable.sh
mvn clean package
docker build -t city-app1 .
cd ..
