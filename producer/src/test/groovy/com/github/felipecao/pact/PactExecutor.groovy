package com.github.felipecao.pact

import org.springframework.test.web.servlet.MockMvc

import java.nio.file.Path

import static com.github.felipecao.pact.matcher.TimestampMatcher.matchesPattern
import static org.hamcrest.Matchers.is
import static org.hamcrest.core.StringStartsWith.startsWith
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class PactExecutor {
    private MockMvc mockMvc
    private Pact pact

    PactExecutor(MockMvc mockMvc, Path pactFile) {
        this.pact = new Pact(pactFile)
        this.mockMvc = mockMvc
    }

    void verifyInteractionWithDescription(String interactionDescription) {
        def interaction = pact.getInteraction(interactionDescription)
        def timestampPattern = interaction.response.matchingRules.body.'$.currentDateTime'.matchers[0].timestamp

        mockMvc.perform(get(interaction.request.path))
                .andExpect(status().is(interaction.response.status))
                .andExpect(status().is(interaction.response.status))
                .andExpect(header().string("Content-Type", startsWith(interaction.response.headers."Content-Type")))
                .andExpect(jsonPath('$.status').value(is(interaction.response.body.status)))
                .andExpect(jsonPath('$.currentDateTime').value(matchesPattern(timestampPattern)))
    }

}
