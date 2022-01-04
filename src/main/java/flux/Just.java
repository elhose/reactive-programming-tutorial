package flux;

import reactor.core.publisher.Flux;
import util.Utils;

public class Just {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2);

        // it's possible to have multiple subscribers to the same flux
        flux.subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
        flux.subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
        flux.subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
        flux.subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
    }
}
