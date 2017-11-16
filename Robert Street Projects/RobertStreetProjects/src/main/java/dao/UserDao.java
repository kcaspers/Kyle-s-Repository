/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;

/**
 *
 * @author kylecaaspers
 */
public interface UserDao {
    
    public void addUser(User user);
    
    public void deleteUser(String username);
    
    public void updateUser(User user);
    
    public User getUserById(int userId);
    
    public User getUserByUsername(String userName);
    
    public List<User> getAllUsers();

    //public List<User> getUsersByAuthority();
}
