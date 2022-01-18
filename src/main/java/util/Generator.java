package util;

import reactor.core.publisher.Flux;

public class Generator {

    public static Flux<String> generateName() {
        return Flux.generate(sink -> {
            final var fullName = Utils.getFAKER().name().fullName();
            Utils.sleep(1);
            System.out.println("Generated name -> "+ fullName);
            sink.next(fullName);
        });
    }

    public static Flux<String> generateAnimal() {
        return Flux.generate(sink -> {
            final var animalName = Utils.getFAKER().animal().name();
            Utils.sleepMillis(500);
            System.out.println("Generated animal -> " + animalName);
            sink.next(animalName);
        });
    }


}
