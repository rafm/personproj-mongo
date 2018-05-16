# Person project

Docker local commands:

- Create docker person network: docker network create --driver bridge person-network

- Start local MongoDB container: docker run --name mongo-person-local -p 27017:27017 --network person-network -d mongo

- Start local Elasticsearch container: docker run --name elasticsearch-person-local -p 9200:9200 -p 9300:9300 --network person-network -e "discovery.type=single-node" -e "cluster.name=elasticsearch" -d docker.elastic.co/elasticsearch/elasticsearch-oss:6.2.4
 
- Start local Kibana container: docker run --name kibana-person-local -p 5601:5601 --network person-network -e ELASTICSEARCH_URL=http://elasticsearch-person-local:9200 -d docker.elastic.co/kibana/kibana-oss:6.2.4

- Build Spring Boot app from Dockerfile: docker build -t rmaia/person-project-mongo .
 
- Start local Spring Boot app container: docker run --name person-project-mongo-local -p 8080:8080 --network person_network -e DB_HOST=mongo-person-local -d rmaia/person-project-mongo