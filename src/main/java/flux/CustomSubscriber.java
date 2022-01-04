package flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import util.Utils;

import java.util.concurrent.atomic.AtomicReference;

public class CustomSubscriber {
    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Subscription: " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("ERROR: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("COMPLETED!");
                    }
                });

        Utils.sleep(2);
        atomicReference.get().request(3);
        System.out.println("FIRST REQUEST");
        Utils.sleep(2);
        atomicReference.get().request(3);
        System.out.println("SECOND REQUEST ");
        // After cancelling there is no way to use the flux, invoking it doesn't throw exception
        System.out.println("CANCELLING");
        atomicReference.get().cancel();

        atomicReference.get().request(3);
        Utils.sleep(2);

    }
}
