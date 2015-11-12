# Run mongo DB
#docker run --name foodrecipes-mongo -d mongo

# Build foodrecipes-server
mvn clean install
docker build -t laloutre42/docker-foodrecipes-server .

# Run foodrecipes-server
docker stop foodrecipes-server
docker rm foodrecipes-server
docker run --name foodrecipes-server -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod --link foodrecipes-mongo:mongo laloutre42/docker-foodrecipes-server