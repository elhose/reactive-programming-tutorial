package sink;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import util.Utils;

// Unicast allows for one subscriber only!
public class Unicast {
    public static void main(String[] args) {

        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<String> flux = sink.asFlux();

        flux.subscribe(Utils.getDefaultSubscriber("Ricky"));
        flux.subscribe(Utils.getDefaultSubscriber("Julian"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("How");
        sink.tryEmitNext("Are");
        sink.tryEmitNext("You");
        sink.tryEmitNext("?");
    }
}
