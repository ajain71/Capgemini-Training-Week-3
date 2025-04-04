import java.util.*;
class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;
    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}
class SocialMediaManager {
    User head = null;
    void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
    }
    User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }
    List<User> findUserByName(String name) {
        List<User> users = new ArrayList<>();
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) users.add(temp);
            temp = temp.next;
        }
        return users;
    }
    void addFriendConnection(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null && id1 != id2) {
            if (!u1.friendIds.contains(id2)) u1.friendIds.add(id2);
            if (!u2.friendIds.contains(id1)) u2.friendIds.add(id1);
        }
    }
    void removeFriendConnection(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null) {
            u1.friendIds.remove(Integer.valueOf(id2));
            u2.friendIds.remove(Integer.valueOf(id1));
        }
    }
    void displayFriends(int userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            if (user.friendIds.isEmpty()) {
                System.out.println("No friends found.");
            }
            for (int id : user.friendIds) {
                User f = findUserById(id);
                if (f != null)
                    System.out.println("ID: " + f.userId + " | Name: " + f.name + " | Age: " + f.age);
            }
        } else {
            System.out.println("User not found.");
        }
    }
    void findMutualFriends(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null) {
            System.out.println("Mutual friends:");
            boolean found = false;
            for (int id : u1.friendIds) {
                if (u2.friendIds.contains(id)) {
                    User f = findUserById(id);
                    if (f != null) {
                        System.out.println("ID: " + f.userId + " | Name: " + f.name);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No mutual friends found.");
            }
        } else {
            System.out.println("One or both users not found.");
        }
    }
    void countFriendsForEachUser() {
        User temp = head;
        while (temp != null) {
            System.out.println("User: " + temp.name + " | Friends Count: " + temp.friendIds.size());
            temp = temp.next;
        }
    }
    void displayAllUsers() {
        User temp = head;
        if (temp == null) {
            System.out.println("No users available.");
            return;
        }
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + " | Name: " + temp.name + " | Age: " + temp.age);
            temp = temp.next;
        }
    }
}
public class SocialMediaSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMediaManager sm = new SocialMediaManager();
        while (true) {
            System.out.println("\n1. Add User\n2. Add Friend Connection\n3. Remove Friend Connection\n4. Display Friends");
            System.out.println("5. Find Mutual Friends\n6. Search User\n7. Count Friends\n8. Display All Users\n9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Age: ");
                    sm.addUser(sc.nextInt(), sc.next(), sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter User1 ID and User2 ID: ");
                    sm.addFriendConnection(sc.nextInt(), sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter User1 ID and User2 ID: ");
                    sm.removeFriendConnection(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    sm.displayFriends(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter User1 ID and User2 ID: ");
                    sm.findMutualFriends(sc.nextInt(), sc.nextInt());
                    break;
                case 6:
                    System.out.println("Search by 1. Name or 2. ID?");
                    int type = sc.nextInt();
                    if (type == 1) {
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        List<User> users = sm.findUserByName(name);
                        if (users.isEmpty()) {
                            System.out.println("No user found with that name.");
                        }
                        for (User u : users) {
                            System.out.println("ID: " + u.userId + " | Name: " + u.name + " | Age: " + u.age);
                        }
                    } else {
                        System.out.print("Enter ID: ");
                        User u = sm.findUserById(sc.nextInt());
                        if (u != null)
                            System.out.println("ID: " + u.userId + " | Name: " + u.name + " | Age: " + u.age);
                        else
                            System.out.println("User not found.");
                    }
                    break;
                case 7:
                    sm.countFriendsForEachUser();
                    break;
                case 8:
                    sm.displayAllUsers();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}