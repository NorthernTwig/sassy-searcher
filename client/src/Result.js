import React, { Component } from 'react'

export default class Result extends Component {
  render() {
    return this.props.result.map(data => (
      <div key={data.name}>
        <h1>{data.name}</h1>
        <h5>{data.score}</h5>
      </div>
    ))
  }
}
