# Person project

Docker local commands:

- Create docker person network: docker network create --driver bridge person-network

- Start local MongoDB container: docker run --name mongo-person-local -p 27017:27017 --network person-network -d mongo

- Start local Elasticsearch container: docker run --name elasticsearch-person-local -p 9200:9200 -p 9300:9300 --network person-network -e "discovery.type=single-node" -e "cluster.name=elasticsearch" -d docker.elastic.co/elasticsearch/elasticsearch-oss:6.2.4
 
- Start local Kibana container: docker run --name kibana-person-local -p 5601:5601 --network person-network -e ELASTICSEARCH_URL=http://elasticsearch-person-local:9200 -d docker.elastic.co/kibana/kibana-oss:6.2.4

- Build Spring Boot app from Dockerfile: docker build -t rafm/person-project-mongo-local .
 
- Start local Spring Boot app container: docker run --name person-project-mongo-local -p 8080:8080 --network person-network -e DB_HOST=mongo-person-local -e ELASTICSEARCH_HOST=elasticsearch-person-local -d rafm/person-project-mongo-local

cURL sample commands:

- GET: curl http://localhost:8080/person/ -v

- GET: curl http://localhost:8080/person/${id} -v

- POST: curl http://localhost:8080/person/ --request POST -H "Content-Type: application/json" --data "{\"name\": \"${name}\", \"country\": \"${country}\"}" -v

- PUT: curl http://localhost:8080/person/${id} --request PUT -H "Content-Type: application/json" --data "{\"name\": \"${name}\", \"country\": \"${country}\"}" -v

- DELETE: curl http://localhost:8080/person/${id} --request DELETE -v