package operators;

import reactor.core.publisher.Flux;
import util.Utils;

public class Callbacks {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                System.out.println("In Create");
                for (int i = 0; i < 5; i++) {
                    fluxSink.next(i);
                }
                fluxSink.complete();
//                fluxSink.error(new RuntimeException("XD"));
                System.out.println("--completed");
            })
            .doFirst(() -> System.out.println("Do First Callback! 1"))
            .doFirst(() -> System.out.println("Do First Callback! 2"))
            .doFirst(() -> System.out.println("Do First Callback! 3"))
            .doOnSubscribe(subscription -> System.out.println("Subscribtion callback! 1-> " + subscription))
            .doOnSubscribe(subscription -> System.out.println("Subscribtion callback! 2-> " + subscription))
            .doOnRequest(longg -> System.out.println("Do on Request callback! -> " + longg))
            .doOnNext(obj -> System.out.println("I'm callback on Next! -> " + obj))
            .doOnError(err -> System.out.println("I'm error from decorator! " + err.getMessage()))
            .doOnComplete(() -> System.out.println("Callback complete!"))
            .doOnTerminate(() -> System.out.println("Terminate callback send!"))
            .doOnCancel(() -> System.out.println("Callback Cancel!"))
            .doFinally(signalType -> System.out.println("Finally doing: 1 " + signalType))
            .doOnDiscard(Object.class, discardHook -> System.out.println("Discard Hook! -> " + discardHook))
            .take(2)
            .doFinally(signalType -> System.out.println("Finally doing: 2 " + signalType))
            .subscribe(Utils.getDefaultSubscriber());
    }
}
