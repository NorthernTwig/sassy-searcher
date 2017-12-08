import React, { Component } from 'react'
import Result from './Result'

class App extends Component {
  state = { query: '', result: [] }

  setQuery = ({ target: { value: query } }) => this.setState({ query })

  search = () => {
    const result = [{ name: 'Islay', score: 1.5 }, { name: 'Denmark', score: 1.0 }]
    this.setState({ result })
  }

  render() {
    return (
      <div>
        <h1>Sloogle</h1>
        <input type="text" onChange={this.setQuery} />
        <button onClick={this.search}>search</button>
        <Result result={this.state.result} />
      </div>
    )
  }
}

export default App
