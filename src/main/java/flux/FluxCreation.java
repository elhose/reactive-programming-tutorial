package flux;

import reactor.core.publisher.Flux;
import util.Utils;

import java.util.List;
import java.util.stream.Stream;

public class FluxCreation {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        Integer[] array = new Integer[]{4, 5, 6};
        Stream<Integer> stream = Stream.of(7, 8, 9);

        Flux.fromArray(array)
            .subscribe(Utils.onNext());

        Flux.fromIterable(list)
            .subscribe(Utils.onNext());

        Flux.fromStream(stream)
            .subscribe(Utils.onNext());

        Flux.range(1, 3)
            .log()
            .map(i -> Utils.getFAKER().funnyName().name())
            .subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
    }

}
