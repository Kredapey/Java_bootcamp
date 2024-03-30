package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int lastId;

    public static synchronized UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        lastId += 1;
        return lastId;
    }

    private UserIdsGenerator() {
        this.lastId = 0;
    }
}
