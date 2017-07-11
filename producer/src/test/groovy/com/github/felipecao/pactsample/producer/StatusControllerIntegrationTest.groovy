package com.github.felipecao.pactsample.producer

import com.github.felipecao.pact.Pact
import com.github.felipecao.pact.PactBroker
import com.github.felipecao.pact.PactsVerifier
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class StatusControllerIntegrationTest {
    
    private MockMvc mockMvc

    private PactsVerifier pactExecutor

    @Autowired
    private WebApplicationContext webApplicationContext

    @Before
    void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
        this.pactExecutor = new PactsVerifier(this.mockMvc)
    }

    @Test
    void "verify status pact"() throws Exception {
        PactBroker broker = new PactBroker()
        List<Pact> pacts = broker.retrievePactsForProvider('StatusEndpoint')

        pactExecutor.verify(pacts)
    }
}
