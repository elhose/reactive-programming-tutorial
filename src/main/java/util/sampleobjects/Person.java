package util.sampleobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.Utils;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = Utils.getFAKER().name().firstName();
        this.age = Utils.getFAKER().random().nextInt(1, 40);
    }
}