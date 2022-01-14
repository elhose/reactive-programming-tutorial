package publishers;

import reactor.core.publisher.Flux;
import util.Utils;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisherRefCount {
    public static void main(String[] args) {
        // share() == publish().refCount(1)
        Flux<String> movieFlux = Flux.fromStream(HotPublisherRefCount::getMovies)
                                     .delayElements(Duration.ofSeconds(2))
                                     .publish()
                                     .refCount(1);
        // if someone resubscribes after emitting, datasource will start again

        movieFlux.subscribe(Utils.getDefaultSubscriber("Ricky"));

        Utils.sleep(5);

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
