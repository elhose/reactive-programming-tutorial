package mono;

import reactor.core.publisher.Mono;
import util.Utils;

public class Just {
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        //nothing happens until subscribe
        mono.subscribe(Utils.onNext());
    }
}
