package flux;

import reactor.core.publisher.Flux;
import util.Utils;

import java.util.concurrent.atomic.AtomicInteger;

public class FluxGenerateWithState {
    public static void main(String[] args) {
        poorFluxGenerateUse();
        betterFluxGenerateUse();
    }

    private static void betterFluxGenerateUse() {
        Flux.generate(() -> 1,
                      (counter, sink) -> {
                          final var countryName = Utils.getFAKER().country().name();
                          sink.next(countryName);
                          System.out.println("INTERNAL state: " + counter);
                          if (counter >= 10 || "CANADA".equalsIgnoreCase(countryName)) {
                              sink.complete();
                          }
                          return counter + 1;
                      }, state -> System.out.println("I'm state Consumer! State: " + state))
            .subscribe(Utils.getDefaultSubscriber("BETTER"));
    }

    private static void poorFluxGenerateUse() {
        AtomicInteger counter = new AtomicInteger();

        // only 1 invoke on next() allowed, it's infinity loop internally
        Flux.generate(synchronousSink -> {
                final var countryName = Utils.getFAKER().country().name();
                counter.getAndIncrement();
                System.out.println("COUNTER -> " + counter.get());
                synchronousSink.next(countryName);
                if ("CANADA".equalsIgnoreCase(countryName) || counter.get() >= 10) {
                    synchronousSink.complete();
                }
            })
            .subscribe(Utils.getDefaultSubscriber());
    }
}
