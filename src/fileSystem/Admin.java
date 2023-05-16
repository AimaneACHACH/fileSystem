package fileSystem;

public class Admin extends User{
	
	public Admin(String username, String password) {
		super(username, password);
	}
	public void createUser(String username,String password,Controller control) {
		User newUser = new User(username,password);
		control.userList.add(newUser);
	}
	public void removerUser(User u,Controller control) {
		control.userList.remove(u);
	}
	public void editUser(User u ,String username,String password) {
		u.setUsername(username);
		u.setPassword(password);
	}
	
	
	
}
