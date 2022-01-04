package mono;

import reactor.core.publisher.Mono;
import util.Utils;

public class Subscribe {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("roll");

        mono.subscribe(Utils.onNext(),
                       Utils.onError(),
                       Utils.onCompletion());

        Mono<Object> monoExcepction = Mono.just("Dummy data")
                                          .map(obj -> {throw new RuntimeException("I'm an Exception");});

        monoExcepction.subscribe(Utils.onNext(),
                                 Utils.onError(),
                                 Utils.onCompletion());
    }
}
