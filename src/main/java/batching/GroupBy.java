package batching;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import util.Utils;

import java.time.Duration;

public class GroupBy {
    public static void main(String[] args) {
        Flux.range(1, 30)
            .delayElements(Duration.ofSeconds(1))
            .groupBy(i -> i % 2)
            .subscribe(integerIntegerGroupedFlux -> {
                Integer key = integerIntegerGroupedFlux.key();
                System.out.println("retrieved key -> " + key);
                integerIntegerGroupedFlux.subscribe(i -> System.out.println("Got " + i + " with Key: " + key));
            });

        Utils.sleep(60);

    }
}
