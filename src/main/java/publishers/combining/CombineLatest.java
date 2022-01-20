package publishers.combining;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;

public class CombineLatest {
    public static void main(String[] args) {
        Flux.combineLatest(getString(), getInteger(), (string ,integer)-> {
            System.out.println("String -> " + string);
            System.out.println("Integer -> " + integer);
            return string + integer;
        }).subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(60);
    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D", "E")
                   .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getInteger() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8)
                   .delayElements(Duration.ofSeconds(3));
    }
}
