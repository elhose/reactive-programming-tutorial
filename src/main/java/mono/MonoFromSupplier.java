package mono;

import reactor.core.publisher.Mono;
import util.Utils;

public class MonoFromSupplier {
    public static void main(String[] args) {
        // Use just whn data is already present/calculated
        Mono<String> mono = Mono.just(getData());

        System.out.println("Do whatever else");

        //it now doesn't wait on initialization
        Mono<String> stringMono = Mono.fromSupplier(MonoFromSupplier::getData);

        System.out.println("Again do whatever else");

//        stringMono.subscribe(Utils.onNext());
    }

    private static String getData() {
        System.out.println("Generating data...");
        Utils.sleep(2);
        return Utils.getFAKER().name().fullName();
    }
}
