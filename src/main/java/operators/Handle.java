package operators;

import reactor.core.publisher.Flux;
import util.Utils;

public class Handle {
    public static void main(String[] args) {
//        handle = filter + map
        Flux.range(1, Integer.MAX_VALUE)
            .handle((integer, synchronousSynk) -> {
                final var countryName = Utils.getFAKER().country().name();
                if ("CANADA".equalsIgnoreCase(countryName)) {
                    synchronousSynk.complete();
                } else {
                    synchronousSynk.next(countryName);
                }
            })
            .subscribe(Utils.getDefaultSubscriber());
    }
}
