import {jsonpGet} from "@/api/ajax";
export const getRoles = (name,pageNo,pageSize,resultFuc) => {
  const data = {
	  name,
	  pageNo,
	  pageSize
  }
  jsonpGet('/admin/getRoles', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const getAuthorizations = (resultFuc) => {
  const data = {

  }
  jsonpGet('/admin/getAuthorizations', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const deleteRoles = (id,resultFuc) => {
  const data = {
		id
  }
  jsonpGet('/admin/deleteRoles', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const updateRolesStat = (id,stat,resultFuc) => {
  const data = {
		id,
		stat
  }
  jsonpGet('/admin/updateRolesStat', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const addRole = (roleName,functions,resultFuc) => {
  const data = {
		roleName,
		functions
  }
  jsonpGet('/admin/addRole', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const getRoleFunctions = (roleId,resultFuc) => {
  const data = {
	roleId
  }
  jsonpGet('/admin/getRoleFunctions', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}

export const updateRole = (roleId,roleName,functions,resultFuc) => {
  const data = {
	roleId,
	roleName,
	functions
  }
  jsonpGet('/admin/updateRole', data, function(result){
		resultFuc(result)
  }, function(error){
		alert(error)
  })
}
