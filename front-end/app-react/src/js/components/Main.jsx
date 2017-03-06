import React from "react"
import { connect } from "react-redux"

import Login from './Login.jsx';

import { Alert } from 'react-bootstrap';
import ReactRedirect from "react-redirect";


import { isAuthenticated } from "../actions/loginActions";


import Bootstrap from 'bootstrap/dist/css/bootstrap.css';


@connect((store) => {
  return {
    authenticated: store.login.authenticated,
    loginFailed: store.login.failed,
    userData: store.login.userData,
  };
})
class Main extends React.Component {

  componentWillMount() {    
    this.props.dispatch(isAuthenticated())       
  }

  componentWillReceiveProps(nextProps) {
      
  }

  constructor() {
    super()    
  }  

  render() {
    const { authenticated, loginFailed, userData } = this.props;   
    
    let html;
    if (authenticated) {
      html = (      
        <div>
            <ReactRedirect location='http://www.google.com'>
              <div>
                Redirecting...
              </div>
            </ReactRedirect>
        </div>
      )
    } else {

    if (loginFailed) {
        html = (
          <div>
            <Alert bsStyle="danger">
                <strong>Login Falhou.</strong>
           </Alert>
           <Login /> 
           </div>
        )
     } else {
      html = (
        <Login />
      )
     }
      
    }
    return ( <div>{ html }</div>)
  }
}


export default Main;