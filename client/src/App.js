import React, { Component } from 'react'
import Result from './Result'
import Spinner from 'react-md-spinner'

class App extends Component {
  state = { query: '', result: [], loading: false }

  setQuery = ({ target: { value: query } }) => this.setState({ query })

  search = async e => {
    if (e.key === 'Enter' && !this.state.loading) {
      this.setState({loading: !this.state.loading})
      const response = await fetch(
        `http://localhost:8080?query=${this.state.query}`,
      )
      const result = await response.json()
      this.setState({ result, loading: !this.state.loading })
    }
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
        <input type="text" onChange={this.setQuery} placeholder="Search" onKeyDown={this.search} />
        {this.state.loading ? <Spinner className="spinner" /> : <Result result={this.state.result} />}
      </main>
    )
  }
}

export default App
