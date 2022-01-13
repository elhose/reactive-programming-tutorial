package operators;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;

public class Delay {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.x", "9");

        Flux.range(1, 10)
            .log()
            .delayElements(Duration.ofSeconds(2))
            .log()
            .timeout(Duration.ofSeconds(1), fallback())
            .log()
            .subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(10);
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 10)
                   .delayElements(Duration.ofMillis(100));
    }

}
