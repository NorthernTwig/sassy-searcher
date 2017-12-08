import React, { Component } from 'react'
import Result from './Result'

class App extends Component {
  state = { query: '', result: [] }

  setQuery = ({ target: { value: query } }) => this.setState({ query })

  search = async () => {
    const response = await fetch(
      `http://localhost:8080?query=${this.state.query}`,
    )
    const result = await response.json()
    this.setState({ result })
  }

  render() {
    return (
      <main>
        <h1>
          <span className="blue">S</span>
          <span className="green">l</span>
          <span className="red">o</span>
          <span className="yellow">o</span>
          <span className="blue">g</span>
          <span className="green">l</span>
          <span className="red">e</span>
        </h1>
        <input type="text" onChange={this.setQuery} placeholder="Search" />
        <button onClick={this.search}>search</button>
        <Result result={this.state.result} />
      </main>
    )
  }
}

export default App
