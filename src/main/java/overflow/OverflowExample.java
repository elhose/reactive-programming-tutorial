package overflow;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import util.Utils;

public class OverflowExample {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                for (int i = 0; i < 500; i++) {
                    fluxSink.next(i);
                    printThreadName("Created " + i);
                }
                fluxSink.complete();
            })
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> {
                Utils.sleepMillis(10);
                printThreadName("onNext " + i);
            })
            .subscribeOn(Schedulers.parallel())
            .subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(10);
    }

    private static void printThreadName(String message) {
        System.out.println(message + " -> Thread: " + Thread.currentThread().getName());
    }
}
