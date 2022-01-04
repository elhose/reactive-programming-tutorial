package flux;

import reactor.core.publisher.Flux;
import util.Utils;

import java.util.List;
import java.util.stream.IntStream;

public class ListVsFlux {
    public static void main(String[] args) {
        List<String> listOfNames = getListOfNames();
        System.out.println(listOfNames);

        getFluxListOfNames().subscribe(Utils.onNext(), Utils.onError(), Utils.onCompletion());
    }

    private static Flux<String> getFluxListOfNames() {
        return Flux.range(1, 5).map(i -> getName());
    }

    private static List<String> getListOfNames() {
        return IntStream.range(0, 5)
                        .mapToObj(i -> getName())
                        .toList();
    }

    private static String getName() {
        Utils.sleep(1);
        return Utils.getFAKER().funnyName().name();
    }
}
