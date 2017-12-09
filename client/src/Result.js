import React, { Component } from 'react'

export default class Result extends Component {
  removeUnderscore = name => {
    return name.replace(/_/g, ' ')
  }

  render() {
    return (
      <div className="result-container">
        {this.props.result.map((data, index) => (
          <div key={data.name} style={{ animationDelay: index * 0.323 + 's' }}>
            <a
              href={`https://en.wikipedia.org/wiki/${data.name}`}
              target="_blank"
            >
              <h3>{this.removeUnderscore(data.name)}</h3>
            </a>
            <h5>{data.score}</h5>
          </div>
        ))}
      </div>
    )
  }
}
