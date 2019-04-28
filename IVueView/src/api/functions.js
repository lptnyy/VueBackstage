import {jsonpGet} from "@/api/ajax";
export const getFunctions = (name,pageNo,pageSize,resultFuc) => {
  const data = {
	  name,
	  pageNo,
	  pageSize
  }
  jsonpGet('/admin/getFunctions', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const getParentFunctions = (resultFuc) => {
  const data = {

  }
  jsonpGet('/admin/getParentFunctions', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const addFunctions = (	name,url,type,parentId,resultFuc) => {
  const data = {
	name,
	url,
	type,
	parentId
  }
  jsonpGet('/admin/addFunctions', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const deleteFunction = (id,resultFuc) => {
  const data = {
	id
  }
  jsonpGet('/admin/deleteFunctions', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const updateFunctionStat = (id,stat,resultFuc) => {
  const data = {
	id,
	stat
  }
  jsonpGet('/admin/updateFunctionStat', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}