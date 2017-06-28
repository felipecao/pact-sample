package com.github.felipecao.pactsample.producer

import com.github.felipecao.pact.Interactions
import com.github.felipecao.pact.Pact
import com.github.felipecao.pact.PactExecutor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

import java.nio.file.Path
import java.nio.file.Paths

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class StatusControllerIntegrationTest {
    
    private static final Path PACT_FILE = Paths.get("pacts", "StatusCLI-StatusEndpoint.json")

    private MockMvc mockMvc

    private PactExecutor pactExecutor

    private Interactions interactions

    @Autowired
    private WebApplicationContext webApplicationContext

    @Before
    void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
        this.pactExecutor = new PactExecutor(this.mockMvc)
        this.interactions = new Interactions(new Pact(PACT_FILE))
    }

    @Test
    void "verify status pact"() throws Exception {
        pactExecutor.verify(interactions.withDescription("a status enquiry"))
    }
}
