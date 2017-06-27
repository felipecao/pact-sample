# pact-sample
A sample project showing how to use Pact to implement consumer-driven tests in Groovy.

## the idea

With the increasing popularity of microservices, it can be quite hard to track the impact of a change done to a given service over consumers of this service. Consumers of this service can be other microservices or user interfaces.

A quite common setting is to have a RESTful Web API acting as a backend component to single-page app, responsible for acting as an UI to the end user. In such a setting, breaking changes can be introduced to the backend without the people responsible for the frontend being aware of it, causing disturbances to the experience provided to user.

One way of overcoming this issue is to use [Consumer-Driven Contracts](https://martinfowler.com/articles/consumerDrivenContracts.html). Such technique proposes that the consumer of the information defines a contract between itself and the producer of the information, and both parties should conform to that contract at all times.

This project is to demonstrate in a very simple and concise manner how to implement Consumer-Driven Contracts between two parties.

You'll notice there are two sub-folders here:

* **consumer**: a very simple command-line interface that accepts only one command: `status`. When invoked, this interface will connect to a backend component via HTTP, retrieve information about its availability and display it to the user. Two pieces of information should be displayed to the user: the backend status (it should be `OK` at all times, hopefully) and the date when that information was provided. Pretty basic, very very simple, our focus is **not** in showing off CLI skills, but rather to show how Consumer-Driven Contracts work on the client side.

* **producer**: a very basic Spring-Boot service, with just one endpoint (`/status`) that accepts `GET` calls. When a `GET /status` request is issued against this service, a JSON response should be sent back (e.g.: `{"status":"OK","currentDateTime":"2017-06-27T13:54:29.214"}`). Again, very basic, very simple and minimalistic, the focus is not to come up with a super fancy service, but rather to demonstrate how a backend component can comply with a contract defined by its consumers.

## the implementation

This project uses [Pact-JVM](https://github.com/DiUS/pact-jvm), a spin-off from [Pact](https://github.com/realestate-com-au/pact) to implement Consumer-Driven Contracts.

The underlying programming language chosen for both consumer and provider is Groovy.
