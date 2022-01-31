package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

class ScenarioNameTest {

    @Test
    void alphabet() {
        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifier.create(just, StepVerifierOptions.create().scenarioName("First letters of alphabet"))
                    .expectNextCount(12)
                    .verifyComplete();
    }

    @Test
    void alphabet2() {
        Flux<String> just = Flux.just("a", "bXD", "c");

        StepVerifier.create(just)
                    .expectNext("a")
                    .as("a-test")
                    .expectNext("b")
                    .as("b-test")
                    .expectNext("c")
                    .as("c-test")
                    .verifyComplete();
    }

}
