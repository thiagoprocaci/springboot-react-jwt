import axios from "axios";
import base64 from "base-64";
import utf8 from "utf8";
import cookie from 'react-cookie';


export function isAuthenticated() {   
  return function(dispatch) {            
     let url =  'http://localhost:8082/api/user'
     axios.post(url)
        .then((response) => {
          dispatch({type: "IS_AUTHENTICATED", payload: ''})
        })
        .catch((err) => {
          dispatch({type: "IS_NOT_AUTHENTICATED", payload: ''})
        })
    }  
}

export function scaLogin(username, password) {
  if(username && password) {
    return function(dispatch) {            
     let url =  'http://localhost:8082/api/authentication?origin=*&username=' + username +  '&password=' + password    
     axios.post(url)
        .then((response) => {                    
          dispatch({type: "LOGIN_SUCCESS", payload: 'success'})
          console.log(response)
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