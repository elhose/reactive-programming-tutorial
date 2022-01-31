package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ExpectNextCountTest {

    @Test
    void test1() {
        Flux<Integer> just = Flux.range(1, 50);

        StepVerifier.create(just)
//                    .expectNextCount(50)
                    .thenConsumeWhile(i -> i < 100)
                    .verifyComplete();
    }
}
