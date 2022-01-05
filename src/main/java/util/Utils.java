package util;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

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
}
