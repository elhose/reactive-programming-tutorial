package mono;

import reactor.core.publisher.Mono;
import util.Utils;

public class MonoFromRunnable {
    public static void main(String[] args) {
        // runnable is executed in current thread, and emits empty once runnable is complete
        Mono.fromRunnable(longProcess())
            .subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
    }

    private static Runnable longProcess() {
        return () -> {
            System.out.println("Doing heavy task");
            Utils.sleep(3);
        };
    }

}
