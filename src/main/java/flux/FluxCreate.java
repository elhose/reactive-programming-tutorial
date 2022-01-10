package flux;

import reactor.core.publisher.Flux;
import util.Utils;

public class FluxCreate {
    public static void main(String[] args) {
        incorrectlyCreatedFlux();
        System.out.println("===========================================================");
        correctlyCreatedFlux();
    }

    /*
    To properly end flux use Flux.cancel()
     */
    private static void correctlyCreatedFlux() {
        Flux.create(fluxSink -> {
                String countryName;
                do {
                    countryName = Utils.getFAKER().country().name();
                    System.out.println("Emmiting -> " + countryName);
                    fluxSink.next(countryName);
                } while (!"CANADA".equalsIgnoreCase(countryName) && !fluxSink.isCancelled());
                fluxSink.complete();
            })
            .take(3)
            .subscribe(Utils.getDefaultSubscriber());
    }

    /*
    This Flux won't stop on take 3, he will go all way till loop is over
     */
    private static void incorrectlyCreatedFlux() {
        Flux.create(fluxSink -> {
                String countryName;
                do {
                    countryName = Utils.getFAKER().country().name();
                    System.out.println("Emmiting -> " + countryName);
                    fluxSink.next(countryName);
                } while (!"CANADA".equalsIgnoreCase(countryName));
                fluxSink.complete();
            })
            .take(3)
            .subscribe(Utils.getDefaultSubscriber());
    }

}
