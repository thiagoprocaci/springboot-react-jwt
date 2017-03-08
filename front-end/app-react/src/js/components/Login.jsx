import React from "react";
import { connect } from "react-redux"


import { Form } from 'react-bootstrap';
import { FormGroup } from 'react-bootstrap';
import { Col } from 'react-bootstrap';
import { FormControl } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import { ControlLabel } from 'react-bootstrap';
import { Checkbox } from 'react-bootstrap';
import { Panel } from 'react-bootstrap';




import { doLogin } from "../actions/loginActions";


@connect((store) => {
  return {
    loginFailed: store.login.failed
  };
})


class Login extends React.Component {


 constructor(props) {
    super(props);    
    this.state = {
      login: "",
      pass: ""
    };
  }

  pressEnter(event) {
  	if(event.charCode == 13){
          this.doLogin();    
    }
  }

  changeEmail(event) {		 
    let fleldVal = event.target.value;    
    this.setState({login: fleldVal, pass : this.state.pass});
  }
  
  changePass(event) {
	let fleldVal = event.target.value;
	this.setState({pass: fleldVal, login : this.state.login});    
  }

  doLogin() {  	
    this.props.dispatch(doLogin(this.state.login, this.state.pass))
  }

  render() {  	

	const { loginFailed } = this.props;

	const formBox = {	    
    	marginTop: '15%',
    	marginLeft: '30%', 
    	width : '30%',
	};	
	
	const btn = {
	    width: '100%',
	}

	const title = "Login Page" ;
    return (
    <div>   
      <Panel header={title} bsStyle="primary" style={formBox}>
      		<div>	    	   
			      <Form horizontal>
				    <FormGroup controlId="formHorizontalEmail">
				      <Col componentClass={ControlLabel} sm={1}>			        
				      </Col>
				      <Col sm={10}>
				        <FormControl type="email" placeholder="Login" name="login" onChange={this.changeEmail.bind(this)} onKeyPress={this.pressEnter.bind(this)}/>
				      </Col>
				    </FormGroup>

				    <FormGroup controlId="formHorizontalPassword">
				      <Col componentClass={ControlLabel} sm={1}>
				        
				      </Col>
				      <Col sm={10}>
				        <FormControl type="password" placeholder="Senha" name="pass" onChange={this.changePass.bind(this)} onKeyPress={this.pressEnter.bind(this)}/>
				      </Col>
				    </FormGroup>				    

				    <FormGroup>
				      <Col smOffset={1} sm={10}>
				        <Button bsStyle="primary"  onClick={this.doLogin.bind(this)} style={btn} >
				          Sign In
				        </Button>
				      </Col>
				    </FormGroup>
				  </Form>
			  </div>
       </Panel> 	    	
	     
     </div>
    );
  }
}


export default Login;