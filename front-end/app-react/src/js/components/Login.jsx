import React from "react";
import { connect } from "react-redux"


import { Form } from 'react-bootstrap';
import { FormGroup } from 'react-bootstrap';
import { Col } from 'react-bootstrap';
import { FormControl } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import { ControlLabel } from 'react-bootstrap';
import { Checkbox } from 'react-bootstrap';

import {Icon} from 'react-fa';


import { scaLogin } from "../actions/loginActions";


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
      senha: ""
    };
  }

  pressEnter(event) {
  	if(event.charCode == 13){
          this.doLogin();    
    }
  }

  changeEmail(event) {		 
    let fleldVal = event.target.value;    
    this.setState({login: fleldVal, senha : this.state.senha});
  }
  
  changePass(event) {
	let fleldVal = event.target.value;
	this.setState({senha: fleldVal, login : this.state.login});    
  }

  doLogin() {  	
    this.props.dispatch(scaLogin(this.state.login, this.state.senha))
  }

  render() {  	

	const { loginFailed } = this.props;

	const formBox = {
	    width: '368px',
    	marginTop: '15%',
    	marginLeft: '40%',
    	backgroundColor: '#ECF0F1',
    	boxShadow: '1px 1px 10px #ccc',
    	borderRadius: '0 0 4px 4px',
    	position: 'absolute',    	
    	fontFamily: 'Open-Sans',
	};	
	const header = {
	    padding: '21px' ,
    	background: 'linear-gradient(295deg,#0277bd 0,#0277bd 51%,#016fb0 51%,#016fb0 100%)',    	
    	borderRadius: 0,
    	fontSize: 25,
    	color: '#fff',
    	textAlign: 'center',    	
	};
	const space = {
		margin : 7,
	}
	const formGroup = {
	    marginBottom: '15px',
    	marginTop: '25px',
    	marginLeft: '-53px',
    	marginRight: '15px',
	}
	const btn = {
	    width: '100%',
	}


    return (
    <div>    	    	
	     <div style={formBox}>	     	
		     <div style={header}>	  		     	     		     
		     	 <Icon name="lock" style={space} />		     	
		     	 Área Restrita
	    	 </div>
	    	 <div>	    	   
			      <Form horizontal style={formGroup}>
				    <FormGroup controlId="formHorizontalEmail">
				      <Col componentClass={ControlLabel} sm={2}>			        
				      </Col>
				      <Col sm={10}>
				        <FormControl type="email" placeholder="Login" name="login" onChange={this.changeEmail.bind(this)} onKeyPress={this.pressEnter.bind(this)}/>
				      </Col>
				    </FormGroup>

				    <FormGroup controlId="formHorizontalPassword">
				      <Col componentClass={ControlLabel} sm={2}>
				        
				      </Col>
				      <Col sm={10}>
				        <FormControl type="password" placeholder="Senha" name="senha" onChange={this.changePass.bind(this)} onKeyPress={this.pressEnter.bind(this)}/>
				      </Col>
				    </FormGroup>				    

				    <FormGroup>
				      <Col smOffset={2} sm={10}>
				        <Button bsStyle="primary"  onClick={this.doLogin.bind(this)} style={btn} >
				          AUTENTICAR
				        </Button>
				      </Col>
				    </FormGroup>
				  </Form>
			  </div>
		  </div>
     </div>
    );
  }
}


export default Login;