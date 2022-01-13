package publishers;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;
import java.util.stream.Stream;

// Cold publisher emits separate dataSource for each subscriber, it's NOT shared
public class ColdPublisher {
    public static void main(String[] args) {
        Flux<String> movieFlux = Flux.fromStream(ColdPublisher::getMovies)
                                     .delayElements(Duration.ofSeconds(2));

        movieFlux.subscribe(Utils.getDefaultSubscriber("Ricky"));

        Utils.sleep(5);

        movieFlux.subscribe(Utils.getDefaultSubscriber("Julian"));

        Utils.sleep(60);
    }

    private static Stream<String> getMovies() {
        System.out.println("Netflix is initialized!!!!!!!");
        return Stream.of("Scene 1",
                         "Scene 2",
                         "Scene 3",
                         "Scene 4",
                         "Scene 5");
    }
}
