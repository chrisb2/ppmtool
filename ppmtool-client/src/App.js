import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Headers from "./components/layout/Headers";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/project/AddProject";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Headers />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
        </div>
      </Router>
    );
  }
}

export default App;
