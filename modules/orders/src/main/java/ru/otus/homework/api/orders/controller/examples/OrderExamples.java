package ru.otus.homework.api.orders.controller.examples;

public class OrderExamples {

    public static final String CREATE = """
            {
              "id": "85407e18-5a3b-42a4-bdad-6750ef6607eb",
              "userId": "0d4f52c5-e1c8-49ec-bac2-33adbec5c148",
              "price": 74,
              "product": "29282f3f-dec7-45ce-98ba-c654706ab71d",
              "address": "Moscow, Svobodi str, h.6, a.4"
            }
            """;

    public static final String GET = """
            {
              "id": "85407e18-5a3b-42a4-bdad-6750ef6607eb",
              "userId": "0d4f52c5-e1c8-49ec-bac2-33adbec5c148",
              "price": 74,
              "product": "29282f3f-dec7-45ce-98ba-c654706ab71d",
              "address": "Moscow, Svobodi str, h.6, a.4",
              "status": PROCESSING
            }
            """;

}
