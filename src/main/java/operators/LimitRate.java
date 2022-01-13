package operators;

import reactor.core.publisher.Flux;
import util.Utils;

public class LimitRate {
    public static void main(String[] args) {
        Flux.range(1, 1000)
            .log()
            .limitRate(100, 90)
            .subscribe(Utils.getDefaultSubscriber());
    }
}
