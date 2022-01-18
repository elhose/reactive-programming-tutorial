package publishers.combining;

import util.Generator;
import util.Utils;

//It starts from "startWith", after one publisher is done, go from initial one
public class StartWith {
    public static void main(String[] args) {
        Generator.generateName()
                 .take(2)
                 .startWith(Generator.generateAnimal().take(3))
                 .subscribe(Utils.getDefaultSubscriber("Ricky"));
    }
}
