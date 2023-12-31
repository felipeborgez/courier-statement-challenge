import exec from 'k6/execution'

import { AWSConfig, SQSClient } from 'https://jslib.k6.io/aws/0.11.0/sqs.js'

const awsConfig = new AWSConfig({
    region: "us-east-1",
    accessKeyId: "AKIAXPGS7V5AWNBDMS3I",
    secretAccessKey: "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"
//    sessionToken: ""
})

const sqs = new SQSClient(awsConfig)
const testQueue = 'http://localhost:4566/000000000000/courier_event_delivery_created'

export default async function () {
    // If our test queue does not exist, abort the execution.
    const queuesResponse = await sqs.listQueues()
    if (queuesResponse.queueUrls.filter((q) => q === testQueue).length == 0) {
        exec.test.abort()
    }

    // Send message to test queue
    await sqs.sendMessage(testQueue, JSON.stringify({
                                                        "deliveryId": "{{$guid}}",
                                                        "courierId": "{{$guid}}",
                                                        "createdTimestamp": "{{$isoTimestamp}}",
                                                        "value": "{{$randomInt}}"
                                                    }));
}
