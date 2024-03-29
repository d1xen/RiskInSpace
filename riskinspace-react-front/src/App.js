import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import './App.css';
import Home from './component/Home';
import Planets from './component/Planets';
import './fonts/Space_Quest_Italic.ttf'

function App() {

  return (
    <div className="App">
    <Router>
      <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/planet" component={Planets} />
      </Switch>
    </Router>
    </div>
  );
}

export default App;