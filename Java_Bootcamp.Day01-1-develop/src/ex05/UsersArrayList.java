package ex05;


public class UsersArrayList implements UsersList {
    private User[] users;
    private int size;
    private int capacity;

    public UsersArrayList() {
        this.capacity = 10;
        this.size = 0;
        this.users = new User[capacity];
    }

    public UsersArrayList(User[] users) {
        this.users = users;
        this.size = users.length;
        this.capacity = users.length;
    }

    @Override
    public void addUser(User user) {
        if (size == capacity) {
            changeCapacity();
        }
        users[size] = user;
        ++size;
    }

    private void changeCapacity() {
        this.capacity *= 2;
        User[] tempUser = new User[capacity];
        for (int i = 0; i < users.length; i++) {
            tempUser[i] = users[i];
        }
        users = tempUser;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; ++i) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("There is no user with this id");
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index >= size) {
            throw new UserNotFoundException("There is no element with this index");
        }
        return users[index];
    }

    @Override
    public int getCountUsers() {
        return size;
    }
}
