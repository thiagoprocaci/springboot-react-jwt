'use strict';

import React from 'react';
import {render} from 'enzyme';
import { shallow } from 'enzyme';
import {expect} from 'chai';


import AfterLogin from '../js/components/AfterLogin.jsx';


function createMockStore(state) {
  return {
    subscribe: () => {},
    dispatch: () => {},
    getState: () => {
      return {...state};
    }
  };
}

function setup(storeState) {  
  return render(<AfterLogin store={createMockStore(storeState)} />);  
}



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
    
    const wrapper = setup(myStore);
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
    const wrapper = setup(myStore);    
    expect(wrapper.find('div')).to.have.html('<div><div>  </div></div>')       
   	

   	
 	
  });

});