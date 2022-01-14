package publishers;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisherAutoConnect {
    public static void main(String[] args) {
        Flux<String> movieFlux = Flux.fromStream(HotPublisherAutoConnect::getMovies)
                                     .delayElements(Duration.ofSeconds(1))
                                     .publish()
                                     .autoConnect(1);
        // second subscriber join on whatever state source has ( emitted all the data, in the middle, before start)

        movieFlux.subscribe(Utils.getDefaultSubscriber("Ricky"));

        Utils.sleep(8);

        System.out.println("Julian is ready to watch!");

        movieFlux.subscribe(Utils.getDefaultSubscriber("Julian"));

        Utils.sleep(60);
    }

    private static Stream<String> getMovies() {
        System.out.println("Movie Theater is initialized!!!!!!!");
        return Stream.of("Scene 1",
                         "Scene 2",
                         "Scene 3",
                         "Scene 4",
                         "Scene 5");
    }
}
