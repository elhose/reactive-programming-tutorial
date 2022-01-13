package util.sampleobjects.flatmap;

import lombok.Data;
import util.Utils;

@Data
public class User {
    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Utils.getFAKER().name().fullName();
    }
}
