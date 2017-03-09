import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import {isAuthenticated} from '../js/actions/loginActions.js';

const mock = new MockAdapter(axios);


mock.onGet('http://localhost:8082/api/user').reply(200, {
  data: 'success'
});




describe('loginActions', () => {

  it('isAuthenticated Success', () => {
  	let response = isAuthenticated()
  	console.log(response) 	
  });

});