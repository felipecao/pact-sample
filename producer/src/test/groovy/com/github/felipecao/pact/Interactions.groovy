package com.github.felipecao.pact

class Interactions {
    private Pact pact

    Interactions(Pact pact) {
        this.pact = pact
    }

    def withDescription(String description) {
        pact.getInteraction(description)
    }
}
