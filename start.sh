#!/bin/sh

build() {
  cd $1/
  ./mvnw clean install
  cd ..
  docker build -t $1 ./$1
}

# build images
build discovery-server
build api-gateway
build products-microservice
build users-microservice
build orders-microservice

# start containers
docker-compose up -d