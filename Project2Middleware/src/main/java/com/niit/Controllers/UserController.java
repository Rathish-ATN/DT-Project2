package com.niit.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.UserDao;
import com.niit.Model.ErrorClazz;
import com.niit.Model.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	public UserController() {
		System.out.println("User Controller Bean is created");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		System.out.println("User Controller");
		try {
			String email = user.getEmail();
			if (!userDao.isEmailUnique(email)) {
				ErrorClazz errorClazz = new ErrorClazz(2, "Email address already Exists");
				return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			userDao.registration(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(1, "Something Went Wrong" + e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.PUT)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
		 System.out.println("User Controller : Login");
		 User validUser=userDao.login(user);
		 if(validUser==null) {
			 System.out.println("Incorrect User Details");
			 ErrorClazz errorClazz=new ErrorClazz(4,"Email/Password is Incorrect");
			 return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.NOT_ACCEPTABLE);
		 }
		 else {
			 System.out.println("Login Success");
			 validUser.setOnline(true);
			 userDao.updateUser(validUser);
			session.setAttribute("loggedinUser", validUser.getEmail());
			 return new ResponseEntity<User>(validUser,HttpStatus.OK);
		 }
		 	 
	}
	

	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email=(String) session.getAttribute("loggedInUser");
		System.out.println("logout method "+email +" email for logged in user" );
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4,"Uauthorized access.. please login.....");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		user.setOnline(false);
		userDao.updateUser(user);
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody User user,HttpSession session){
		//Check for Authentication
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4,"Uauthorized access.. please login.....");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		try {
		userDao.updateUser(user);}catch(Exception e) {
			ErrorClazz errorClazz=new ErrorClazz(5,"unable to update user detials.....");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
