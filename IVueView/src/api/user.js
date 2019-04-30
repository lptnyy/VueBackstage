import {jsonpGet} from "@/api/ajax";
export const getUsers = (name,pageNo,pageSize,resultFuc) => {
  const data = {
	  name,
	  pageNo,
	  pageSize
  }
  jsonpGet('/admin/getUsers', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}
export const updateStat = (id,stat,resultFuc) => {
  const data = {
	  id,
	  stat
  }
  jsonpGet('/admin/updateStat', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}
export const deleteUser = (id,resultFuc) => {
  const data = {
	  id
  }
  jsonpGet('/admin/deleteUser', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}