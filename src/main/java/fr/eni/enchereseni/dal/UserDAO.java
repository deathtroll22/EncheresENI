package fr.eni.enchereseni.dal;

import fr.eni.enchereseni.bo.User;

public interface UserDAO {
	
	public void createUser (User user);
	public boolean isUsernameTaken(String username);
	public boolean isEmailTaken(String email);
	
	public User login(String username, String password);
	public User getUserByLoginIdentifier(String username);
	
	public User getUserProfileByUsername(String username);
	
	public void updateMyProfil (User user);

	public void deleteUser(Integer userID);

	public User getUserById(int userId);
	public void updateUserCredit(Integer userID, int credit);


}
