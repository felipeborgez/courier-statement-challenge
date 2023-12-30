import http from 'k6/http';
import { sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';


export const options = {
  // A number specifying the number of VUs to run concurrently.
  vus: 2,
  // A string specifying the total duration of the test run.
  duration: '10s',

  // The following section contains configuration options for execution of this
  // test script in Grafana Cloud.
  //
  // See https://grafana.com/docs/grafana-cloud/k6/get-started/run-cloud-tests-from-the-cli/
  // to learn about authoring and running k6 test scripts in Grafana k6 Cloud.
  //
  // ext: {
  //   loadimpact: {
  //     // The ID of the project to which the test is assigned in the k6 Cloud UI.
  //     // By default tests are executed in default project.
  //     projectID: "",
  //     // The name of the test in the k6 Cloud UI.
  //     // Test runs with the same name will be grouped.
  //     name: "script.js"
  //   }
  // },

  // Uncomment this section to enable the use of Browser API in your tests.
  //
  // See https://grafana.com/docs/k6/latest/using-k6-browser/running-browser-tests/ to learn more
  // about using Browser API in your test scripts.
  //
  // scenarios: {
  //   // The scenario name appears in the result summary, tags, and so on.
  //   // You can give the scenario any name, as long as each name in the script is unique.
  //   ui: {
  //     // Executor is a mandatory parameter for browser-based tests.
  //     // Shared iterations in this case tells k6 to reuse VUs to execute iterations.
  //     //
  //     // See https://grafana.com/docs/k6/latest/using-k6/scenarios/executors/ for other executor types.
  //     executor: 'shared-iterations',
  //     options: {
  //       browser: {
  //         // This is a mandatory parameter that instructs k6 to launch and
  //         // connect to a chromium-based browser, and use it to run UI-based
  //         // tests.
  //         type: 'chromium',
  //       },
  //     },
  //   },
  // }
};

// The function that defines VU logic.
//
// See https://grafana.com/docs/k6/latest/examples/get-started-with-k6/ to learn more
// about authoring k6 scripts.
//
export default function() {

 const randomUUID = uuidv4();
  console.log(randomUUID); // 35acae14-f7cb-468a-9866-1fc45713149a

    let couriers = [
        "178e054f-bab2-4bfe-ab28-0a07d716650f",
        "4bef711d-3b09-4bd1-88d0-7497bf51d9a7",
        "26e5e478-5fcc-43c9-ac52-a1bf79e734f4",
        "8bf27789-3d68-4528-87c3-01e21ee59b45",
        "7f217696-23fb-4261-b557-7fdd9010793e",
        "c681006d-1266-4a21-9cb3-39031adabe3f",
        "305962a9-aa4c-4737-9434-cb7a326fd334",
        "4d7d5cb6-81a0-47ed-9bec-50918f0e4736",
        "2914df33-4c89-4718-9d35-12f4b5738015",
        "599db3b8-8fd7-423b-a2a4-4f89c74dbb77"
    ]


  let url = 'http://localhost:8080/v1/couriers/event';
  let data = {
     deliveryId: "{{$guid}}",
     courierId: "{{$guid}}",
     createdTimestamp: "{{$isoTimestamp}}",
     value: "{{$randomInt}}"
 }
  http.post(url, JSON.stringify(data), {
      headers: { 'Content-Type': 'application/json' },
    });
  sleep(1);
}
