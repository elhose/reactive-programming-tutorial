package schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import util.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class Parallel {
    public static void main(String[] args) {

        final var list = Collections.synchronizedList(new ArrayList<Integer>());

        Flux.range(1, 1000)
            .parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(i -> printThreadName("next " + i))
            .subscribe(list::add);

        Utils.sleep(3);

        System.out.println(list.size());
    }

    private static void printThreadName(String message) {
        System.out.println(message + " -> Thread: " + Thread.currentThread().getName());
    }
}
