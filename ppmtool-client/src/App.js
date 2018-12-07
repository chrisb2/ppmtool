import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Headers from "./components/layout/Headers";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Headers />
        <Dashboard />
      </div>
    );
  }
}

export default App;
