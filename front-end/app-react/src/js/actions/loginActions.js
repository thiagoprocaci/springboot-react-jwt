import axios from "axios";
import base64 from "base-64";
import utf8 from "utf8";


let authenticated = false;

export function isAuthenticated() {
	return {
    type: "IS_AUTHENTICATED",
    payload: {
      authenticated: authenticated,
    }
  }
}

export function scaLogin(username, password) {
  if(username && password) {
    return function(dispatch) {      
      //let url =  'http://localhost:8082/api/authentication?username=' + username +  '&password=' + base64.encode(utf8.encode(password))
      let url =  'http://localhost:8082/api/authentication?origin=*&username=' + username +  '&password=' + password 
     


     axios.post(url)
        .then((response) => {
          dispatch({type: "LOGIN_SUCCESS", payload: 'success'})
        })
        .catch((err) => {
          dispatch({type: "LOGIN_FAILED", payload: err})
        })
    }
  }
  return {
    type: "LOGIN_EMPTY",
    payload: {
      message : "Empty username or password.",
    }
  }
}