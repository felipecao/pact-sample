
let pact = require('pact');

it('does something simple', () => {
  console.log('hix')
});


// 'use strict';
//
// let request = require('request');
// let path = require('path');
// let chai = require('chai');
// let pact = require('pact');
// let chaiAsPromised = require('chai-as-promised');
// let expect = chai.expect;
//
// chai.use(chaiAsPromised);
//
// describe('StatusFrontEnd pact with StatusEndpoint', () => {
//
//   const provider = pact({
//     consumer: 'StatusFrontEnd',
//     provider: 'StatusEndpoint',
//     port: 1234,
//     log: path.resolve(process.cwd(), 'logs', 'pact.log'),
//     dir: path.resolve(process.cwd(), 'pacts'),
//     logLevel: 'INFO',
//     spec: 2
//   })
//
//   const EXPECTED_BODY = {
//     status: 'OK',
//     currentDateTime: '2017-07-04T17:02:53.582'
//   }
//
//   beforeEach((done) => {
//     provider.setup().then(() => {
//       provider.addInteraction({
//         given: 'status endpoint is up',
//         uponReceiving: 'a status enquiry',
//         withRequest: {
//           method: 'GET',
//           path: '/status'
//         },
//         willRespondWith: {
//           status: 200,
//           headers: { 'Content-Type': 'application/json' },
//           body: EXPECTED_BODY
//         }
//       })
//     })
//     .then(() => done())
//   });
//
//   it('should generate a list of TODOs for the main screen', () => {
//     request('http://localhost:1234/status', function (error, response, body) {
//       console.log(`response = `, response);
//       console.log(`body = `, body);
//     });
//   });
//
//   afterEach(() => {
//     provider.finalize()
//   });
//
// });
