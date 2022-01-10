package flux;

import reactor.core.publisher.Flux;
import util.Utils;

public class TakeOperator {
    public static void main(String[] args) {
        Flux.range(1, 10)
            .log()
            .take(3) // cancels after n-th item
            .log()
            .subscribe(Utils.getDefaultSubscriber());
    }
}
