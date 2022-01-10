package util;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    @Getter
    private static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return object -> System.out.println("Received: " + object);
    }

    public static Consumer<Throwable> onError() {
        return err -> System.out.println("ERROR: " + err.getMessage());
    }

    public static Runnable onCompletion() {
        return () -> System.out.println("Completed!");
    }

    @SneakyThrows
    public static void sleep(int seconds) {
        int timeToSleep = seconds * 1000;
        Thread.sleep(timeToSleep);
    }

    public static Subscriber<Object> getDefaultSubscriber() {
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> getDefaultSubscriber(String name) {
        return new DefaultSubscriber(name);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class DefaultSubscriber implements Subscriber<Object> {
        private String name = "Default Subscriber";

        @Override
        public void onSubscribe(Subscription subscription) {
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(Object o) {
            System.out.println(name + " received: " + o);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(name + " ERROR: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println(name + " completed!");
        }
    }

}
