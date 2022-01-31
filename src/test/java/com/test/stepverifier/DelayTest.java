package com.test.stepverifier;

import com.github.javafaker.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import util.Utils;

import java.time.Duration;

class DelayTest {

    @Test
    void test1() {
        Mono<Country> countryMono = Mono.fromSupplier(() -> Utils.getFAKER().country());

        StepVerifier.create(countryMono)
                    .assertNext(Assertions::assertNotNull)
                    .verifyComplete();
    }

    @Test
    void test2() {
        Mono<Country> countryMono = Mono.fromSupplier(() -> Utils.getFAKER().country())
                                        .delayElement(Duration.ofSeconds(3));

        StepVerifier.create(countryMono)
                    .assertNext(Assertions::assertNotNull)
                    .expectComplete()
                    .verify(Duration.ofSeconds(4));
    }
}
