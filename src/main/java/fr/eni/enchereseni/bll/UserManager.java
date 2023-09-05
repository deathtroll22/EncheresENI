package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.User;

public interface UserManager {
	public void createAccount(User account) throws ManagerException;
    public User login(String loginIdentifier, String password) throws ManagerException;
    public User getUserProfileByUsername(String username) throws ManagerException;
    public void updateMyProfil (User user) throws ManagerException;
	public void deleteUser(Integer userID);
    
    //public void deleteAccount(User user) throws ManagerException;
    // 2 public void rememberMe(User user) throws ManagerException;
    // 2 public void forgotPassword(String loginIdentifier) throws ManagerException;
    // 3 public void viewPoints();

}
