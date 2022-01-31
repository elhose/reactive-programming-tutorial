package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class VirtualTimeTest {

    @Test
    void nonBlocking() {
        StepVerifier.withVirtualTime(this::timeConsumingFlux)
                    .expectSubscription()
                    .expectNoEvent(Duration.ofSeconds(4))
                    .thenAwait(Duration.ofSeconds(30))
                    .expectNext("1A", "2A", "3A")
                    .verifyComplete();
    }

    @Test
    void blocking() {
        StepVerifier.create(timeConsumingFlux())
                    .expectNext("1A", "2A", "3A")
                    .verifyComplete();

    }

    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 3)
                   .delayElements(Duration.ofSeconds(5))
                   .map(i -> i + "A");
    }
}
