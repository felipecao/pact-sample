'use strict';

let request = require('superagent');
let path = require('path');
let chai = require('chai');
let pact = require('pact');
let chaiAsPromised = require('chai-as-promised');
let expect = chai.expect;
let { term } = pact.Matchers;

chai.use(chaiAsPromised);

describe('StatusFrontEnd pact with StatusEndpoint', () => {

  const MOCK_PORT = Math.floor(Math.random() * 999) + 9000;

  const PROVIDER = pact({
    consumer: 'StatusFrontEnd',
    provider: 'StatusEndpoint',
    port: MOCK_PORT,
    log: path.resolve(process.cwd(), 'logs', 'pact.log'),
    dir: path.resolve(process.cwd(), 'pacts'),
    logLevel: 'INFO',
    spec: 2
  })

  const EXPECTED_BODY = {
    status: 'OK',
    currentDateTime: '2017-07-04T17:02:53.582'
  }

  before((done) => {
    PROVIDER.setup().then(() => {
      PROVIDER.addInteraction({
        given: 'status endpoint is up',
        uponReceiving: 'a status enquiry',
        withRequest: {
          method: 'GET',
          path: '/status',
          headers: { 'Accept': 'application/json' }
        },
        willRespondWith: {
          status: 200,
          headers: { 'Content-Type': 'application/json' },
          body: {
            status: 'OK',
            currentDateTime: term({
              generate: '2017-07-04T17:02:53.582',
              matcher: '\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}'
            })
          }
        }
      })
    })
    .then(() => done());
  });

  after(() => {
    PROVIDER.finalize()
  });

  it('returns the expected body', (done) => {
    request
      .get(`http://localhost:${MOCK_PORT}/status`)
      .set({ 'Accept': 'application/json' })
      .then((response) => {
        expect(response.body.status).to.eql('OK');
        expect(response.body.currentDateTime).to.match(/\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.\d{3}/);
      })
      .then(done);
  })

  it('successfully verifies', () => PROVIDER.verify());

});
