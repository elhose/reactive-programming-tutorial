package operators;

import reactor.core.publisher.Flux;
import util.Utils;
import util.sampleobjects.Person;

public class SwitchOnFirst {
    public static void main(String[] args) {
        Flux.range(1, 10)
            .map(i -> new Person())
            .switchOnFirst((signal, personFlux) -> signal.isOnNext() && signal.get().getAge() >= 18 ?
                            personFlux : Flux.just(new Person("XD", 88)))
            .subscribe(Utils.getDefaultSubscriber("TRANS ->"));
    }
}
