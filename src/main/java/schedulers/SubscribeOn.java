package schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import util.Utils;

// It's just mainly used to point on which thread pool pipeline should be consumed
public class SubscribeOn {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                                    printThreadName("create ");
                                    fluxSink.next(1);
                                })
                                .doOnNext(i -> printThreadName("next " + i));

//        it's still all done in single thread!
        flux
                .doFirst(() -> printThreadName("doFirst3")) // Parallel is doing the work
                .subscribeOn(Schedulers.parallel())
                .doFirst(() -> printThreadName("doFirst2"))// BoundedElastic is now doing the work
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("doFirst1")) // Main Thread is doing the work
                .subscribe(consumer -> printThreadName("sub " + consumer));

        Utils.sleep(1);
        System.out.println("============================================================");

//        "last" Scheduler counts finally!, runnable allows for parallelization
        Runnable runnable = () -> flux
                .doFirst(() -> printThreadName("doFirst3")) // Parallel is doing the work
                .subscribeOn(Schedulers.parallel())
                .doFirst(() -> printThreadName("doFirst2"))// BoundedElastic is now doing the work
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("doFirst1")) // Main Thread is doing the work
                .subscribe(consumer -> printThreadName("sub " + consumer));

        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }

        Utils.sleep(60);
    }

    private static void printThreadName(String message) {
        System.out.println(message + " -> Thread: " + Thread.currentThread().getName());
    }
}
