package com.github.felipecao.pact

import au.com.dius.pact.model.Interaction
import au.com.dius.pact.model.RequestResponseInteraction
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import au.com.dius.pact.model.Pact

import static com.github.felipecao.pact.matcher.TimestampMatcher.matchesPattern
import static org.hamcrest.Matchers.is
import static org.hamcrest.core.StringStartsWith.startsWith
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class PactsVerifier {
    private MockMvc mockMvc

    PactsVerifier(MockMvc mockMvc) {
        this.mockMvc = mockMvc
    }

    void verify(List<Pact> pacts) {
        pacts.each {
            it.interactions.each { interaction ->
                verifyInteraction(interaction)
            }
        }
    }

    private void verifyInteraction(RequestResponseInteraction interaction) {
        def timestampPattern = interaction.response.matchingRules.rules.body.matchingRules.'$.currentDateTime'.rules[0]
        def responseBody = new JsonSlurper().parseText(interaction.response.body.value)

        mockMvc.perform(get(interaction.request.path))
                .andExpect(status().is(interaction.response.status))
                .andExpect(header().string("Content-Type", startsWith(interaction.response.headers."Content-Type")))
                .andExpect(jsonPath('$.status').value(is(responseBody.status)))
                .andExpect(jsonPath('$.currentDateTime').value(matchesPattern(timestampPattern)))
    }

}
