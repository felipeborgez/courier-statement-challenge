# Courier Statement - Challenge

## Run project
The solution diagram is [system-solution-diagram.pdf](system-solution-diagram.pdf)

## Run project
```shell
docker-compose up -d && ./gradlew bootRun
```
The API is running on localhost:8080


## REST Endpoints:

### Return delivery transactions by period and courier.
Example:
```shell
curl --location 'localhost:8080/v1/couriers/a1738301-69e2-42cb-9cd4-3054759802bf/transactions?startAt=2023-12-01&endAt=2023-12-30'
```

```json
[
  {
    "deliveryId": "45be397e-c53d-4376-8b57-7ca5a5fc46cb",
    "sumTotal": 16800,
    "sumAdjustments": 5600,
    "sumBonuses": 5600
  }
]
```

### Return the weekly courier statement for a specific courier.
Example:
```shell
curl --location 'localhost:8080/v1/couriers/a1738301-69e2-42cb-9cd4-3054759802bf/statement'
```
```json
[
  {
    "weekStartAt": "2023-12-31",
    "weekEndAt": "2024-01-06",
    "totalAmountPaidToCourier": 16800,
    "totalAmountPaidToCourierDueToAdjustments": 5600,
    "totalAmountPaidToCourierDueToBonuses": 5600
  }
]
```

## Creating events (examples)
### DeliveryCreated
```shell
curl --location 'localhost:8080/v1/event/delivery' \
--header 'Content-Type: application/json' \
--data '{
    "deliveryId": "1af6585d-6227-4464-8378-4490142f0697",
    "courierId": "35edd68b-e60d-4812-913b-c42233f7fb6b",
    "createdTimestamp": "2023-12-31T03:28:53.046Z",
    "value": "413"
}'
```
### AdjustmentModified
```shell
curl --location 'localhost:8080/v1/event/adjustment' \
--header 'Content-Type: application/json' \
--data '{
    "adjustmentId": "dbfd89f3-fe9e-4d8e-ae02-36abaf2d3d5f",
    "deliveryId": "a64e1821-11da-4750-8f7e-c14062094235",
    "modifiedTimestamp": "2023-12-31T03:29:05.810Z",
    "value": "-200"
}'
```
### BonusModified
```shell
curl --location 'localhost:8080/v1/event/bonus' \
--header 'Content-Type: application/json' \
--data '{
    "bonusId": "3ffb808d-6238-4dab-b080-94bbfaa21fa2",
    "deliveryId": "a64e1821-11da-4750-8f7e-c14062094235",
    "modifiedTimestamp": "2023-12-31T03:29:15.394Z",
    "value": "200"
}'
```


[//]: # (## Run k6)

[//]: # (This script will load some random and concurrent events o event queues)

[//]: # (```shell)

[//]: # (k6 run k6/script.js)

[//]: # (```)