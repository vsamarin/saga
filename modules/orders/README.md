## Build Docker Image

```shell
docker build . --platform linux/amd64 -t vsamarin/orders:1.1.1
```

## Push Docker Image

```shell
docker push vsamarin/orders:1.1.1
```

## Run Docker Image

```shell
docker run -p 8080:8000 --env-file .env vsamarin/orders:1.1.1
```
