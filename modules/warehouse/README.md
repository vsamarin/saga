## Build Docker Image

```shell
docker build . --platform linux/amd64 -t vsamarin/warehouse:1.0.0
```

## Push Docker Image

```shell
docker push vsamarin/warehouse:1.0.0
```

## Run Docker Image

```shell
docker run -p 8080:8000 --env-file .env vsamarin/warehouse:1.0.0
```
