package schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import util.Utils;

public class PubOnSubOn {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                                    printThreadName("create ");
                                    for (int i = 0; i < 4; i++) {
                                        fluxSink.next(i);
                                    }
                                    fluxSink.complete();
                                })
                                .doOnNext(i -> printThreadName("next " + i));

        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(obj -> printThreadName("doOnNextFromSubscribe " + obj))
                .subscribeOn(Schedulers.parallel())
                .subscribe(consumer -> printThreadName("sub " + consumer));

        Utils.sleep(5);
    }

    private static void printThreadName(String message) {
        System.out.println(message + " -> Thread: " + Thread.currentThread().getName());
    }
}
