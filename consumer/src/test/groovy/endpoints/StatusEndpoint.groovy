package endpoints

import groovyx.net.http.RESTClient

class StatusEndpoint {
    private String baseUrl
    private RESTClient restClient

    StatusEndpoint(String baseUrl) {
        this.baseUrl = baseUrl
        this.restClient = new RESTClient(baseUrl)
    }

    def status() {
        restClient.get([path: '/status']).data
    }
}
