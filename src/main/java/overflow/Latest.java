package overflow;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import util.Utils;

public class Latest {
    public static void main(String[] args) {
//        75% of X
        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                for (int i = 0; i < 500; i++) {
                    fluxSink.next(i);
                    printThreadName("Created " + i);
                    Utils.sleepMillis(1);
                }
                fluxSink.complete();
            })
            .onBackpressureLatest()
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> {
                Utils.sleepMillis(10);
                printThreadName("onNext " + i);
            })
//            .subscribeOn(Schedulers.parallel())
            .subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(10);
    }

    private static void printThreadName(String message) {
        System.out.println(message + " -> Thread: " + Thread.currentThread().getName());
    }
}
