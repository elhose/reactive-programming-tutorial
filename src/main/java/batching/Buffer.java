package batching;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;

// Allows for grouping published items
public class Buffer {
    public static void main(String[] args) {
        eventStream()
//                .take(4) // Even when take is less than buffer, onComplete it will proceed
//                .bufferTimeout(5, Duration.ofSeconds(2)) // Whichever is true, it will group by that
                .buffer(5,1) // allows for skipping part of data
                .subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                   .map(i -> "Event: " + i);
    }
}
