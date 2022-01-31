package sink;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import util.Utils;

public class Replay {
    public static void main(String[] args) {
        // cache EVERYTHING, make sure every subscriber gets each value
        Sinks.Many<String> sink = Sinks.many().replay().all();

        Flux<String> flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("How");
        sink.tryEmitNext("Are");
        sink.tryEmitNext("You");

        flux.subscribe(Utils.getDefaultSubscriber("Ricky"));
        flux.subscribe(Utils.getDefaultSubscriber("Julian"));

        sink.tryEmitNext("?");

        flux.subscribe(Utils.getDefaultSubscriber("Bubbles"));

        sink.tryEmitNext("FINALE");
    }
}
