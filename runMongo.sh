#!/usr/bin/env bash

# Run mongo DB
docker stop foodrecipes-mongo && \
docker rm foodrecipes-mongo && \
docker run --name foodrecipes-mongo -v /home/vagrant/data:/data/db mongo
