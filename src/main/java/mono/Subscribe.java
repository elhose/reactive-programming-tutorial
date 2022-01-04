package mono;

import reactor.core.publisher.Mono;

public class Subscribe {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("roll");

        mono.subscribe(item -> System.out.println(item + " the ball"),
                err -> System.out.println("ERROR: " + err.getMessage()),
                () -> System.out.println("I'm completed!"));

        Mono<Object> monoExcepction = Mono.just("Dummy data").map(obj -> {throw new RuntimeException("I'm an Exception");});

        monoExcepction.subscribe(item -> System.out.println(item + " the ball"),
                                 err -> System.out.println("ERROR: " + err.getMessage()),
                                 () -> System.out.println("I'm completed!"));
    }
}
