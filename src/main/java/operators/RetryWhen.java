package operators;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import util.Utils;

import java.time.Duration;

public class RetryWhen {
    public static void main(String[] args) {

        getIntegersAlwaysThrowsError()
//                .retry(2)
//                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .retryWhen(Retry.from(retrySignalFlux -> retrySignalFlux
                        .doOnNext(retrySignal -> {
                            System.out.println("TOTAL RETRIES: " + retrySignal.totalRetries());
                            System.out.println("FAILURE: " + retrySignal.failure());
                        })
                        .handle((retrySignal, synchronousSink) -> {
                            String message = retrySignal.failure().getMessage();
                            if (message.equals("NOOO")) {
                                synchronousSink.error(retrySignal.failure());
                            }
                            if (message.equals("OOPS")) {
                                synchronousSink.next(888);
                            }
                        })
                        .delayElements(Duration.ofSeconds(1))))
                        .subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(60);
    }

    private static Flux<Integer> getIntegersAlwaysThrowsError() {
        return Flux.range(1, 5)
                   .delayElements(Duration.ofMillis(500))
                   .doOnSubscribe((subscription) -> System.out.println("-SUB"))
                   .doOnComplete(() -> System.out.println("-COMP"))
                   .map(RetryWhen::mapperThrowingExceptions)
                   .doOnError(throwable -> System.out.println("-ERR " + throwable.getMessage()));
    }

    private static Integer mapperThrowingExceptions(Integer integer) {
        Boolean first = Utils.getFAKER().random().nextBoolean();
        Boolean second = Utils.getFAKER().random().nextBoolean();
        if (first && second) {
            throw new RuntimeException("OOPS");
        }
        if (!first && !second) {
            throw new RuntimeException("NOOO");
        }
        return integer;
    }
}
