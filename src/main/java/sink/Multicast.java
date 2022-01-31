package sink;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import util.Utils;
// Here it's perfectly fine to use multiple subscribers
public class Multicast {
    public static void main(String[] args) {

//        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer(); // default Buffer for "history"
        Sinks.Many<String> sink = Sinks.many().multicast().directAllOrNothing(); // no "history"

        Flux<String> flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("How");
        sink.tryEmitNext("Are");
        sink.tryEmitNext("You");

//      onBackpressureBuffer -> Values are kept in buffer if there is no instant subscription
//      directAllOrNothing -> no buffer = no history
        flux.subscribe(Utils.getDefaultSubscriber("Ricky"));
        flux.subscribe(Utils.getDefaultSubscriber("Julian"));

        sink.tryEmitNext("?");

        flux.subscribe(Utils.getDefaultSubscriber("Bubbles"));

        sink.tryEmitNext("FINALE");
    }
}
