## Setup Conductor

Перейти по ссылке [https://developer.orkescloud.com/](https://developer.orkescloud.com/) и авторизоваться.
Создать client_id и client_secret для подключения клиента к Conductor:
![img.png](./images/img_01.png)
![img.png](./images/img_02.png)
Подставить соответствующие значения в настройки deployment :

```yaml
env:
    -   name: CONDUCTOR_AUTH_KEY
        value: spk39518fc1b-def0-11ef-aeb1-c259128d4f69
    -   name: CONDUCTOR_AUTH_SECRET
        value: qezQQ336JPvw1azvCCfbOyXy6FuCnEKpIEaZotxLPdfcoqkr
```

___

Импортировать task-и:

![img_1.png](./images/img_05.png)

Вставить содержимое файлов из директории [task](./task) и нажать Save:

![img_1.png](./images/img_06.png)

___

Импортировать workflow:
![img.png](./images/img_03.png)
Вставить содержимое файлов _[compensation_workflow.json](./workflow/compensation_workflow.json)_,
_[direct_workflow.json](./workflow/direct_workflow.json)_ и нажать Save:

![img.png](./images/img_04.png)

___

Получиться следующий результат:
Direct Flow:
![img.png](images/img_07.png)
Compensation Flow:
![img.png](images/img_08.png)

___

Запуск Direct Flow осуществляется вызовом метода сервиса orders /api/v1/workflow:
![img.png](images/img_09.png)
Результаты выполнения отображаются тут:
![img.png](images/img_10.png)
Тут же можно отслеживать параметры с которыми запускались шаги flow:
![img.png](images/img_11.png)
Запуск Compensation Flow осуществляется автоматический worker-ами в случае возникновения ошибки в обработке задачи.
