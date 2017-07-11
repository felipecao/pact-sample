package com.github.felipecao.pact

import au.com.dius.pact.model.Pact
import au.com.dius.pact.provider.junit.loader.PactBrokerLoader

class PactBroker {

    private PactBrokerLoader loader

    PactBroker() {
        Properties props = new Properties();

        props.load(new File("src/test/resources/pact-broker.properties").newInputStream())

        loader = new PactBrokerLoader(
                props.getProperty('pactBrokerHost'),
                props.getProperty('pactBrokerPort'),
                props.getProperty('pactBrokerProtocol')
        )

        loader.authentication = new PactAuthBuilder()
                .withUserName(props.getProperty('pactBrokerUser'))
                .withPassword(props.getProperty('pactBrokerPassword'))
                .build()
    }

    List<Pact> retrievePactsForProvider(String providerName) {
        return loader.load(providerName)
    }
}
