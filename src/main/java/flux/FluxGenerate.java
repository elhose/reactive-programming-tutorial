package flux;

import reactor.core.publisher.Flux;
import util.Utils;

public class FluxGenerate {
    public static void main(String[] args) {
        // only 1 invoke on next() allowed, it's infinity loop internally
        Flux.generate(synchronousSink -> {
                final var countryName = Utils.getFAKER().country().name();
                synchronousSink.next(countryName);
                if ("CANADA".equalsIgnoreCase(countryName)) {
                    synchronousSink.complete();
                }
            })
            .subscribe(Utils.getDefaultSubscriber());

    }
}
