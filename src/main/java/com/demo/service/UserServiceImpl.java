package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.User;
import com.demo.respository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userrepository;

	@Override
	public User saveUser(User uobj) {
		Optional<User> optional = this.userrepository.findById(uobj.getUserId());
		if(optional.isPresent()) {
			System.out.println("User Details already exists...");
		}
		User adduobj = this.userrepository.save(uobj);
		return adduobj;
	}

	@Override
	public User updateUser(User uobj, int uid) {
		Optional<User> userOptional = this.userrepository.findById(uid);
		User uObj = null;
		User updatedData = null;
		
		if(userOptional.isPresent()) {
			System.out.println("Record Exists and Ready to update..");
			
			uObj = userOptional.get();
			
			uObj.setUserName(uobj.getUserName());
			uObj.setPassword(uobj.getPassword());
			
			updatedData = this.userrepository.save(uObj);
			
		}
		return updatedData;
	}

	@Override
	public User getUserById(int uid) {
		Optional<User> optional = this.userrepository.findById(uid);
		
		User uObj = null;
		
		if(optional.isPresent()) {
			System.out.println("Record is present and is ready for update...");
			
			uObj = optional.get();
		}else {
			System.out.println("Record not present..");
		}
		return uObj;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userrepository.findAll();
	}

	@Override
	public boolean delUser(int uid) {
		Optional<User> optional = this.userrepository.findById(uid);
		
		boolean status = false;
		
		if(optional.isPresent()) {
			System.out.println("User is present and is ready for delete ...");
			this.userrepository.delete(optional.get());
			status = true;
		}else {
			System.out.println("User doesn't exists ...");
		}
		return status;
	}

}
