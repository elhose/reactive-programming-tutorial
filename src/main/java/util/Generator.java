package util;

import reactor.core.publisher.Flux;

import java.time.Duration;

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

    public static Flux<String> generateFlights(String airlineName) {
        return Flux.range(1, Utils.getFAKER().random().nextInt(1, 5))
                   .delayElements(Duration.ofSeconds(1))
                   .map(i -> airlineName + " " + Utils.getFAKER().random().nextInt(100, 999))
                   .filter(i -> Utils.getFAKER().random().nextBoolean());
    }


}
