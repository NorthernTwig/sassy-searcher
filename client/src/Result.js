import React, { Component } from 'react'

export default class Result extends Component {
  render() {
    return (
      <div className="result-container">
        {this.props.result.map(data => (
          <div key={data.name}>
            <a href={`https://en.wikipedia.org/wiki/${data.name}`} target="_blank">
              <h3>{data.name}</h3>
            </a>
            <h5>{data.score}</h5>
          </div>
        ))}
      </div>
    )
  }
}
