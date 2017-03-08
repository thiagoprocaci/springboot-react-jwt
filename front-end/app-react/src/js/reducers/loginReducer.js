export default function reducer(state={    
    authenticated : false ,
    failed : false,
    userData : null,
  }, action) {
    switch (action.type) {             
      case "IS_AUTHENTICATED": {
        return {
          ...state,
          authenticated : true,
          failed : false,
          userData : action.payload,
        }
      }      
      case "IS_NOT_AUTHENTICATED": {
        return {
          ...state,
          authenticated : false,
          userData : null,
        }
      }            
      case "LOGIN_SUCCESS": {
        return {
          ...state,
          authenticated : true,
          failed : false,
          userData : null,
        }
      }
      case "LOGIN_FAILED": {
        return {
          ...state,
          authenticated : false,          
          failed : true,
          userData : null,
        }
      }
      case "LOGIN_EMPTY": {
        return {
          ...state,
          authenticated : false,          
          failed : true,
          userData : null,
        }
      }      
    }
    return state
}

