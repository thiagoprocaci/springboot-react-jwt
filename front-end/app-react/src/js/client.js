import React from "react"
import ReactDOM from "react-dom"
import { Provider } from "react-redux"
import { Router, Route, IndexRoute, hashHistory } from "react-router";

import Main from "./components/Main.jsx"



import store from "./store"

require('file-loader?name=index.html!../index.html');

const app = document.getElementById('app')

ReactDOM.render(<Provider store={store}>
  <Router history={hashHistory}>
    <Route path="/" component={Main}>
      <IndexRoute component={Main}/>      
      <Route path="main" name="main" component={Main}></Route>     
    </Route>
  </Router>
</Provider>, app);
