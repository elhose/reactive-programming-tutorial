package sink;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import util.Utils;

public class Sink {
    public static void main(String[] args) {

//        mono  1value/ empty / error
        Sinks.One<Object> sink = Sinks.one();

        Mono<Object> mono = sink.asMono();

        // TODO: 20/01/2022 PERFECTLY FINE 
        mono.subscribe(Utils.getDefaultSubscriber("Ricky"));
        mono.subscribe(Utils.getDefaultSubscriber("Julian"));

//        sink.tryEmitValue("Hello!");
//        sink.tryEmitEmpty();
//        sink.tryEmitError(new RuntimeException("XD"));
        sink.emitValue("hi", errorHandler());

        // FIXME: 20/01/2022 NOT ALLOWED!!
        sink.emitValue("should throw error", errorHandler());

    }

    private static Sinks.EmitFailureHandler errorHandler() {
        return  (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        };
    }

}
