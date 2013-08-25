package com.topicMe.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topicMe.dao.AbstractBackendDAO;
import com.topicMe.model.businessdomain.User;
 
/**
 *
 * User DAO
 *
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Repository 
public class UserDAO extends AbstractBackendDAO implements IUserDAO  {

	
	
	
  
 
    /**
     * Add User
     *
     * @param  User user
     */
  
    public void addUser(User user) {
        getEntityManager().persist(user);
    }
 
    /**
     * Delete User
     *
     * @param  User user
     */
    public void deleteUser(User user) {
    	 getEntityManager().remove(user);
    }
 
    /**
     * Update User
     *
     * @param  User user
     */
    public void updateUser(User user) {
    	 getEntityManager().merge(user);
    }
 
    /**
     * Get User
     *
     * @param  int User Id
     * @return User
     */
    public User getUserById(int id) {
    	List<User> list =  getEntityManager().createQuery("from User where id=?")
                                            .setParameter(0, id).getResultList();
        return (User)list.get(0);
    }
 
    /**
     * Get User List
     *
     * @return List - User list
     */
    public List<User> getUsers() {
        List<User> list =  getEntityManager().createQuery("from User").getResultList();
        return list;
    }
 
}
