package com.github.felipecao.pact

import au.com.dius.pact.provider.junit.loader.PactBrokerAuth

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class PactAuthBuilder {

    private String username

    private String password

    PactAuthBuilder withUserName(String username) {
        this.username = username
        return this
    }

    PactAuthBuilder withPassword(String password) {
        this.password = password
        return this
    }

    PactBrokerAuth build() {
        def auth = mock(PactBrokerAuth.class)

        when(auth.username()).thenReturn('dXfltyFMgNOFZAxr8io9wJ37iUpY42M')
        when(auth.password()).thenReturn('O5AIZWxelWbLvqMd8PkAVycBJh2Psyg1')
        when(auth.scheme()).thenReturn('Basic')

        return auth
    }

}
