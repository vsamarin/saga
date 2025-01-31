```shell
newman run saga-collection.json -e saga-environment.json --verbose
```

Процесс асинхронный, так что после запуска flow добавил задержку, чтобы дождаться результата выполнения.
