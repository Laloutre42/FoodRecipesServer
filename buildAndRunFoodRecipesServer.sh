#!/usr/bin/env bash

# Build and run foodrecipes-server
mvn clean install && \
docker build -t laloutre42/docker-foodrecipes-server . && \
docker stop foodrecipes-server && \
docker rm foodrecipes-server && \
docker run --name foodrecipes-server -p 8080:8080 -e SPRING_PROFILES_ACTIVE=qlf --link foodrecipes-mongo:mongo laloutre42/docker-foodrecipes-server

