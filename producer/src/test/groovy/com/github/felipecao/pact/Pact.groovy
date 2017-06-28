package com.github.felipecao.pact

import groovy.json.JsonSlurper

import java.nio.file.Path

class Pact {
    private def json

    Pact(Path pactFile) {
        def jsonSlurper = new JsonSlurper()
        json = jsonSlurper.parse(pactFile.toFile())
    }

    def getInteraction(String description) {
        json.interactions.find {it.description == description}
    }
}
