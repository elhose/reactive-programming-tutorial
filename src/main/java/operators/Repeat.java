package operators;

import reactor.core.publisher.Flux;
import util.Utils;

import java.util.concurrent.atomic.AtomicInteger;

// Used to repeat whole process
public class Repeat {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    public static void main(String[] args) {
        getIntegers()
                .map(integer -> ATOMIC_INTEGER.getAndIncrement())
                .repeat(2, () -> Utils.getFAKER().random().nextBoolean())
                .subscribe(Utils.getDefaultSubscriber());
    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                   .doOnSubscribe(s -> System.out.println("SUBSCRIBED!"))
                   .doOnComplete(() -> System.out.println("COMPLETED!"));
    }
}
