package com.topicMe.user.service;

import java.util.List;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topicMe.model.businessdomain.User;
import com.topicMe.user.dao.IUserDAO;
 
/**
 *
 * User Service
 *
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
	
	 
    IUserDAO userDAO;
 
    /**
     * Add User
     *
     * @param  User user
     */
    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }
 
    /**
     * Delete User
     *
     * @param  User user
     */
    @Transactional(readOnly = false)
    
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }
 
    /**
     * Update User
     *
     * @param  User user
     */
    @Transactional(readOnly = false)
    
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }
 
    /**
     * Get User
     *
     * @param  int User Id
     */
    
    public User getUserById(int id) {
        return getUserDAO().getUserById(id);
    }
 
    /**
     * Get User List
     *
     */
    
    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }
 
    /**
     * Get User DAO
     *
     * @return IUserDAO - User DAO
     */
    public IUserDAO getUserDAO() {
        return userDAO;
    }
 
    /**
     * Set User DAO
     *
     * @param IUserDAO - User DAO
     */
    // UserDAO is injected...
   	@Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
   	
    @PrePersist
    public void testPrePersist(){
    	System.out.println("Test");
    }
}