https://github.com/XiaosongWen/personal_web_drive

get swagger ui: http://localhost:8080/swagger-ui/index.html

## work plan
1. todo list using nextjs, nextui, spring boot and mysql, with login
2. simple upload, download file, and delete file
3. enable s3 to allow user to share file

## note
### API
https://stackoverflow.com/questions/72025894/list-differences-dto-vo-entity-domain-model

```bash
docker run -d \
  --name pgvecto-rs \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=immich \
  -p 5432:5432 \
  -v C:\Users\wxsst\Desktop\project\immich-app\db:/var/lib/postgresql/data \  
  docker.io/tensorchord/pgvecto-rs:pg14-v0.2.0@sha256:90724186f0a3517cf6914295b5ab410db9ce23190a2d9d0b9dd6463e3fa298f0
```
```
docker run -d --name postgres-ssd -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=immich -p 5432:5432 -v C:\Users\wxsst\Desktop\project\immich-app\db:/var/lib/postgresql/data docker.io/tensorchord/pgvecto-rs:pg14-v0.2.0@sha256:90724186f0a3517cf6914295b5ab410db9ce23190a2d9d0b9dd6463e3fa298f0
```

#### openApi
```
openapi-generator-cli generate \
    -i immich-openapi-specs.json \
    -g java \
    -o ./out \
    --additional-properties=library=spring-boot,apiPackage=com.example.api,modelPackage=com.example.model
 
```

openapi-generator-cli generate -i immich-openapi-specs.json -g java -o ./out --additional-properties=library=spring-boot