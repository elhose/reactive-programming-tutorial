package batching;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

// Batches without skipping
public class Window {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    public static void main(String[] args) {
        eventStream()
//                .window(4)
                .window(Duration.ofSeconds(2))
                .flatMap(stringFlux -> stringFlux.doOnNext(string -> System.out.println("GOT " + string))
                                                 .doOnError(Throwable::printStackTrace)
                                                 .doOnComplete(() -> System.out.println(
                                                         "COMPLETED BATCH " + ATOMIC_INTEGER.getAndIncrement())))
//                .subscribe(Utils.getDefaultSubscriber());
                .subscribe();

        Utils.sleep(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                   .map(i -> "Event: " + i);
    }
}
