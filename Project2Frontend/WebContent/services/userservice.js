/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8080/Project2Middleware"
	userService.registration=function(user){
		var url=BASE_URL+"/register";
		console.log("user ",user)
		return $http.post(url,user)
	}
	
	userService.login=function(user){
		var url=BASE_URL +"/login";
		console.log("user service",url)
		return $http.put(url,user)
	}
	
	/*userService.getAllJobs=function(){
		var url=BASE_URL +"/getalljobs"
		return $http.get(url)
	}*/
	
	userService.logout=function(){
		var url=BASE_URL +"/logout";
		return $http.put(url)
	}
	
	userService.updateProfile=function(user){
		var url=BASE_URL + "/updateprofile"
		return $http.put(url,user)
	}
	return userService;
})