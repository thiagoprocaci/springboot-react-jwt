import React from "react";
import { connect } from "react-redux"


import { Alert } from 'react-bootstrap';
import { Button } from 'react-bootstrap';

import { doLogout } from "../actions/loginActions";


@connect((store) => {
  return {
    
    userData: store.login.userData,
  };
})

class AfterLogin extends React.Component {

  doLogout() { 
  	this.props.dispatch(doLogout())
  }

  render() {  	
  	const { userData } = this.props;  
	
	const btn = {
	    margin: '30px',
	}

	let html;
	    if (userData) {
	      html = (      
	        <div>   
		       	<Alert bsStyle="info">
		                <strong>Your Token: {localStorage.token}</strong> <br />
		         You are logged as {userData.username}. If you want to logout click on the button below.
		         </Alert>   	
			     <Button bsStyle="primary"  onClick={this.doLogout.bind(this)} style={btn}  >
						         Logout
				</Button>
		     </div>
	      )
	    } else {
	    	html = ( <div>  </div>)
	    }
    return ( <div>{ html }</div>)
  }
}


export default AfterLogin;