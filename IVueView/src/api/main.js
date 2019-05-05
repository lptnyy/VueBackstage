import {jsonpGet} from "@/api/ajax";
export const leftMenu = (userId,resultFuc) => {
  const data = {
	  userId
  }
  jsonpGet('/admin/leftMenu', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}