package mono;

import reactor.core.publisher.Mono;
import util.Utils;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {
    public static void main(String[] args) {
        // It doesn't return anything, because Completable Future uses internally Fork Join Pool
        // Main thread is terminated before it has chance to complete
        Mono.fromFuture(MonoFromFuture::getName)
            .subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Utils.getFAKER().name().fullName());
    }

}
