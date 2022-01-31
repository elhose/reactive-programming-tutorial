package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ExpectErrorTest {

    @Test
    void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(() -> new RuntimeException("OOPS"));

        Flux<Integer> concat = just.concatWith(error);

        StepVerifier.create(concat)
                    .expectNext(1, 2, 3)
                    .verifyError(RuntimeException.class);
    }
}
