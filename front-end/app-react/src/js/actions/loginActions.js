import axios from "axios";
import base64 from "base-64";
import utf8 from "utf8";



export function isAuthenticated() {   
  return function(dispatch) {            
     let url =  'http://localhost:8082/api/user'
     var config = {
      headers: {'Authorization':  'Bearer ' + localStorage.getItem( "token" )}
    };

     axios.get(url, config)
        .then((response) => {
          dispatch({type: "IS_AUTHENTICATED", payload: ''})
        })
        .catch((err) => {
          console.log(err)
          dispatch({type: "IS_NOT_AUTHENTICATED", payload: ''})
        })
    }  
}

export function doLogin(username, password) {
  if(username && password) {
    return function(dispatch) {            
     let url =  'http://localhost:8082/api/authentication?username=' + username +  '&password=' + base64.encode(utf8.encode(password))    
     axios.post(url)
        .then((response) => {                    
          dispatch({type: "LOGIN_SUCCESS", payload: response.data})        
          localStorage.setItem( "token", response.data.token );          
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