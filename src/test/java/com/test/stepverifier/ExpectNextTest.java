package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ExpectNextTest {

    @Test
    void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4);

        StepVerifier.create(just)
                    .expectNext(1)
                    .expectNext(2)
                    .expectNext(3)
                    .expectNext(4)
                    .verifyComplete();
    }

    @Test
    void test2() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4);

        StepVerifier.create(just)
                    .expectNext(1, 2, 3, 4)
                    .verifyComplete();
    }

}
