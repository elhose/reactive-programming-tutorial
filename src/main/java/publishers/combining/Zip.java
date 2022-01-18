package publishers.combining;

import reactor.core.publisher.Flux;
import util.Utils;

// Gets "Fully build" final object, meaning, number of consumed items will be equal to minimal amount of produced
// items of all consumers
public class Zip {
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
            .subscribe(Utils.getDefaultSubscriber());
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5).map(i -> "body");
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2).map(i -> "engine");
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6).map(i -> "tires");
    }
}
