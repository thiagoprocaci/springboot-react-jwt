import React from "react"
import { connect } from "react-redux"

import Login from './Login.jsx';
import AfterLogin from './AfterLogin.jsx';

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
    if(nextProps.authenticated && nextProps.userData === null) {
      this.props.dispatch(isAuthenticated())        
    }      
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
             <AfterLogin /> 
        </div>
      )
    } else {

    if (loginFailed) {
        html = (
          <div>
            <Alert bsStyle="danger">
                <strong>Something went wrong.</strong>
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