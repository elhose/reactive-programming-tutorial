package mono;

import reactor.core.publisher.Mono;

public class Just {
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        //nothing happens until subscribe
        mono.subscribe(System.out::println);
    }
}
