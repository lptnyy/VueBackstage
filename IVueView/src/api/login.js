import {jsonpGet} from "@/api/ajax";
export const login = (userName, password, resultFuc) => {
  const data = {
    userName,
    password
  }
  jsonpGet('/login', data, function(result){
			resultFuc(result)
  }, function(error){
			alert(error)
  })
}