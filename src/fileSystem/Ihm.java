package fileSystem;
import java.util.Arrays;
import java.util.Scanner;

public class Ihm {
	private Controller controller;
    
    public Ihm(Controller controller) {
        this.controller = controller;
    }
	public void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
	}
	
	public void show(Folder f) {
		System.out.println(f.getPath() + "/" + f.getNom() + ">");
		int n = 0;
		for (Elements x: f.getElementsList()) {
			System.out.println("\t" + n + " .- " + x.getNom() +"\t"+x.getTaille());
			n++;
		}
	}
	
	public void userMenu(Folder f,User user) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n[N]Create folder [C]Create file [0-9]Show Element \n[F]Find Element [S]Change user [B]Return");
		if (user instanceof Admin) {
			System.out.println("[Z]Admin Menu\n");
		}
		String userInput = scanner.nextLine(); 
		switch(userInput) {
			case "N" :
			case "n" :
				System.out.println("Name of the new folder :");
				String nomDossier = scanner.nextLine();
				f.createElement(1, 0, nomDossier);
				clearConsole();
				show(f);
				userMenu(f,user);
				break ;
			case "C" :
			case "c" :	
				System.out.println("Name of the new file :");
				String nomFichier = scanner.nextLine();
				System.out.println("Size of the file :");
				int tailleFichier = scanner.nextInt();
				f.createElement(0, tailleFichier, nomFichier);
				clearConsole();
				show(f);
				userMenu(f,user);
				break ;
			case "F" :
			case "f" :
				System.out.println("Name of the element :");
				String elementName = scanner.nextLine();
				boolean found = false;
				for(Elements x : f.getElementsList()) {
					if(x.getNom().equals(elementName)) {
						found = true;
						if (x instanceof File) {
							System.out.println(x.getNom() + " (" + x.getTaille() + ")");
							show(f);
							userMenu(f,user);
							break;
						} else {
							show((Folder)x);
							userMenu((Folder)x,user);
							break;
						}
					}
				}
				if (!found) {
					System.out.println("Element not found.");
				}
				break;
			case "S" :
			case "s" :
				clearConsole();
				return;
			case "B":
			case "b" :
			    if (f.getPath().equals(user.getHome().getPath())) {
			        break;
			    } else {
			    	String[] pathParts = f.getPath().split("/");
			        String newPath = Arrays.stream(pathParts)
			                                .limit(pathParts.length-1)
			                                .reduce("", (a,b) -> a + "/" + b)
			                                .replaceFirst("^/", "");
			        Folder parentFolder = findFolder(newPath, user.getHome());
			        if (parentFolder == null) {
			            System.out.println("Error: folder not found");
			        } else {
			            clearConsole();
			            show(parentFolder);
			            userMenu(parentFolder, user);
			        }
			    }
			    break;
			case "Z" :
			case "z" :
				if (user instanceof Admin) {
					Admin admin = (Admin) user;
					adminMenu(admin,f);
				}
				System.out.println("Invalide commande");
				show(f);
				userMenu(f,user);
				break;
			default :
				
				try {
					int n = Integer.parseInt(userInput);
					int id = 0;
					clearConsole();
					for(Elements x : f.getElementsList()) {
						if (id == n) {
							if (x instanceof File) {
								System.out.println(x.getNom() + " (" + x.getTaille() + ")");
								show(f);
								userMenu(f,user);
								break;
							} else {
								show((Folder)x);
								userMenu((Folder)x,user);
								break;
							}
						}
						id++;
					}

					show(f);
					userMenu(f,user);
					break;
				}catch(NumberFormatException e) {
					clearConsole();
					System.out.println("Invalide commande");
					show(f);
					userMenu(f,user);
				}
		}
	}
	private Folder findFolder(String path, Folder currentFolder) {
	    if (path.equals(currentFolder.getPath())) {
	        return currentFolder;
	    } else {
	        for (Elements e : currentFolder.getElementsList()) {
	            if (e instanceof Folder) {
	                Folder subfolder = (Folder) e;
	                Folder foundFolder = findFolder(path, subfolder);
	                if (foundFolder != null) {
	                    return foundFolder;
	                }
	            }
	        }
	        return null;
	    }
	}
	public void adminMenu(Admin admin,Folder f) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[N]Create user [S]Delete user [E]Modify user [L]User liste [B]Return");
		String userInput = scanner.nextLine(); 
		switch(userInput) {
			case "N" :
			case "n" :
				clearConsole();
				System.out.println("Name of the new user :");
				String username = scanner.nextLine();
				System.out.println("Password :");
				String password = scanner.nextLine();
				admin.createUser(username, password,this.controller);
				clearConsole();
				System.out.println("User created successfully !");
				adminMenu(admin,f);
				break;
			case "S":
			case "s" :
				clearConsole();
				System.out.println("User name :");
				String userToRemove = scanner.nextLine();
				User userFound = findUser(userToRemove);
				if(userFound!=null) {
					admin.removerUser(userFound,this.controller);
					clearConsole();
					System.out.println("User deleted successfully !");
				}else {
					System.out.println("User not found !");
				}
				adminMenu(admin,f);
				break;
			case "E":
			case "e" :
				clearConsole();
				System.out.println("Name of the user :");
				String userToEdit = scanner.nextLine();
				User userToModify = findUser(userToEdit);
				if(userToModify!=null) {
					System.out.println("New user name :");
					String newUsername = scanner.nextLine();
					System.out.println("New password :");
					String newPassword = scanner.nextLine();
					admin.editUser(userToModify, newUsername, newPassword);
					clearConsole();
					System.out.println("User modified successfully");
				}else {
					System.out.println("User not found !");
				}
				adminMenu(admin,f);
				break;
			case "B":
			case "b" :
				clearConsole();
				show(f);
				userMenu(f,admin);
				break;
			case "L":
			case "l" :
				clearConsole();
				for (User x : controller.getUserList()) {
					System.out.println(x.getUsername());
				}
				adminMenu(admin,f);
				break;
				
			default:
				clearConsole();
				System.out.println("Invalide input !");
				adminMenu(admin,f);
				break;
			}
		}

	public User findUser(String username) {
		for(User u : this.controller.userList) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
}
			
