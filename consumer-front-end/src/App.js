import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  constructor() {
    super();
    this.retrieveProducerStatus = this.retrieveProducerStatus.bind(this);
    this.state = {
      producer: {
        status: 'loading...',
        currentDateTime: 'loading...'
      }
    };
  }

  componentDidMount() {
    window.setInterval(this.retrieveProducerStatus, 1000);
  }

  retrieveProducerStatus() {
    fetch(`http://localhost:8080/status`)
      .then(result => result.json())
      .then(json => this.setState({
        producer: {
          status: json.status,
          currentDateTime: json.currentDateTime
        }
      }));
  }

  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>pact-sample :: consumer</h2>
        </div>
        <p className="App-intro">
          <strong>status</strong>: {this.state.producer.status} <br/>
          <strong>current date time</strong>: {this.state.producer.currentDateTime}
        </p>
      </div>
    );
  }
}

export default App;
