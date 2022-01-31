package com.test.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

class ContextTest {
    private static final String USER_KEY = "user";

    @Test
    void test1() {
        StepVerifier.create(getWelcomeMessage())
                    .verifyError(RuntimeException.class);
    }

    @Test
    void test2() {
        StepVerifier.create(getWelcomeMessage(),
                            StepVerifierOptions.create().withInitialContext(Context.of(USER_KEY, "Julian")))
                    .expectNext("Welcome Julian")
                    .verifyComplete();
    }

    private Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey(USER_KEY)) {
                return Mono.just("Welcome " + contextView.get(USER_KEY));
            }
            return Mono.error(() -> new RuntimeException(" NO USER :/"));
        });
    }
}
