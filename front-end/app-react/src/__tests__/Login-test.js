'use strict';

import React from 'react';
import {render} from 'enzyme';
import { shallow, mount } from 'enzyme';
import {expect} from 'chai';


import Login from '../js/components/Login.jsx';
import {createMockStore} from './functions.js';


describe('Login', () => {

  it('renders correctly', () => {
  	const myStore = {
  		"login" : {
  			"userData" : {  
  	         "failed": false
  	    	}
      	}
  	} 
  	
    const wrapper = mount(<Login store={createMockStore(myStore)} />); 
    //console.log(wrapper.debug())
    expect(wrapper.find('input')).to.have.length(2);      
    expect(wrapper.find('button')).to.have.length(1);   
    expect(wrapper.find('button')).to.have.html('<button type="button" class="btn btn-primary" style="width: 100%;">Sign In</button>');
  });  

});