import axios from "axios";
import base64 from "base-64";
import utf8 from "utf8";



export function isAuthenticated() {   
  return function(dispatch) {            
     let url =  'http://localhost:8082/api/user'
     var config = {
      headers: {'Authorization':  'Bearer ' +"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg"}
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

export function scaLogin(username, password) {
  if(username && password) {
    return function(dispatch) {            
     let url =  'http://localhost:8082/api/authentication?origin=*&username=' + username +  '&password=' + password    
     axios.post(url)
        .then((response) => {                    
          dispatch({type: "LOGIN_SUCCESS", payload: response.data})
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