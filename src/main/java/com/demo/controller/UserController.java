package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.IUserService;

@RestController
@RequestMapping("api/v1")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	private ResponseEntity<?> responseEntity;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> saveUserHandler(@RequestBody User uobj){
		User newUser = this.userService.saveUser(uobj);
		responseEntity = new ResponseEntity<>(newUser,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUersHandler(){
		List<User> allUsers = this.userService.getAllUsers();
		responseEntity = new ResponseEntity<>(allUsers,HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/updateUser/{uid}")
	public ResponseEntity<?> updateUserHandler(@RequestBody User uobj,@PathVariable int uid)
	{
		User newUser = this.userService.updateUser(uobj,uid);
		responseEntity = new ResponseEntity<>(newUser,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/getUserById/{uid}")
	public ResponseEntity<?> getUserByIdHandler(@PathVariable int uid)
	{
		User uObj = this.userService.getUserById(uid);
		responseEntity = new ResponseEntity<>(uObj,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/delUserById/{uid}")
	public ResponseEntity<?> delUserByIdHandler(@PathVariable int uid){
		boolean status = this.userService.delUser(uid);
		responseEntity = new ResponseEntity<>("User Deleted Successfully ...",HttpStatus.OK);
		return responseEntity;
	}

}
