package publishers.combining;

import util.Generator;
import util.Utils;

// Concat just "adds" another publisher to the current pipeline, after last one ends emitting data
public class Concat {
    public static void main(String[] args) {
        Generator.generateName()
                 .take(2)
                 .concatWith(Generator.generateAnimal().take(3))
                 .subscribe(Utils.getDefaultSubscriber("Ricky"));
    }
}
