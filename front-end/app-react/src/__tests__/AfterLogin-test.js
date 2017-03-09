'use strict';

import React from 'react';
import {render} from 'enzyme';
import { shallow, mount } from 'enzyme';
import {expect} from 'chai';


import AfterLogin from '../js/components/AfterLogin.jsx';
import {createMockStore} from './functions.js';


describe('AfterLogin', () => {

  it('renders correctly', () => {
  	const myStore = {
  		"login" : {
  			"userData" : {  
  	         "username":"admin"
  	    	}
      	}
  	} 
  	localStorage.removeItem("token");
  	localStorage.setItem( "token", "abc" );  
    const wrapper = render(<AfterLogin store={createMockStore(myStore)} />); 
    expect(wrapper.find('strong')).to.have.html('<strong>Your Token: abc</strong>')       
    expect(wrapper.find('button')).to.have.html('<button style="margin:30px;" type="button" class="btn btn-primary">Logout</button>')       
    localStorage.removeItem("token");
 	
  });


  it('renders nothing', () => {
  	const myStore = {
  		"login" : {
  			
      	}
  	} 	
    localStorage.removeItem("token");
    const wrapper = render(<AfterLogin store={createMockStore(myStore)} />);     
    expect(wrapper.find('div')).to.have.html('<div><div>  </div></div>')       
  });

  it('on click logout', () => {
    const myStore = {
      "login" : {
        "userData" : {  
             "username":"admin"
          }
        }
    }    
    localStorage.removeItem("token");
    localStorage.setItem( "token", "abc" );
    const wrapper = mount(<AfterLogin store={createMockStore(myStore)} />);   
    expect(wrapper.props().doLogout).to.be.defined;
    localStorage.removeItem("token");         
  });

  

});