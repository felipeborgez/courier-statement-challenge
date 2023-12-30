#!/bin/sh
awslocal --endpoint-url=http://localhost:4566 sqs create-queue --queue-name courier_event_delivery_created >/dev/null
awslocal --endpoint-url=http://localhost:4566 sqs create-queue --queue-name courier_event_adjustment_modified >/dev/null
awslocal --endpoint-url=http://localhost:4566 sqs create-queue --queue-name courier_event_bonus_modified >/dev/null
#awslocal --endpoint-url=http://localhost:4566 sns create-topic --name courier_event_bonus_modified >/dev/null