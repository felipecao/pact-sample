const pact = require('@pact-foundation/pact-node');
const path = require('path');
const opts = {
  pactUrls: [path.resolve(__dirname, '../pacts/statusfrontend-statusendpoint.json')],
  pactBroker: 'https://test.pact.dius.com.au',
  pactBrokerUsername: 'dXfltyFMgNOFZAxr8io9wJ37iUpY42M',
  pactBrokerPassword: 'O5AIZWxelWbLvqMd8PkAVycBJh2Psyg1',
  consumerVersion: '1.0.0'
};

pact.publishPacts(opts)
  .then(() => {
    console.log('Pact contract publishing complete!')
  })
  .catch(e => {
    console.log('Pact contract publishing failed: ', e)
  });
