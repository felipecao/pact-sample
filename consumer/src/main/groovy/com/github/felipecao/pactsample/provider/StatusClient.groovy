package com.github.felipecao.pactsample.provider

import groovyx.net.http.RESTClient

class StatusClient {
    private static final String BASE_URL = "http://localhost:8080"
    private RESTClient restClient

    StatusClient() {
        this.restClient = new RESTClient(BASE_URL)
    }

    def retrieveProviderStatus() {
        restClient.get([path: '/status']).data
    }
}
