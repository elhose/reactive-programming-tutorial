package publishers.combining;

import reactor.core.publisher.Flux;
import util.Generator;
import util.Utils;

// Combine multiple publishers into one pipeline
public class Merge {
    public static void main(String[] args) {
        Flux<String> quatar = Generator.generateFlights("Quatar");
        Flux<String> emirates = Generator.generateFlights("Emirates");
        Flux<String> stringFlux = Generator.generateFlights("AmericanAirlines");

        Flux.merge(quatar, emirates, stringFlux).subscribe(Utils.getDefaultSubscriber());

        Utils.sleep(10);
    }
}

