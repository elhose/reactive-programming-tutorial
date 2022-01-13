package operators;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import util.Utils;
import util.sampleobjects.Person;

import java.util.Locale;
import java.util.function.Function;

public class Transform {
    public static void main(String[] args) {
        final Function<Flux<Person>, Publisher<Person>> personTransformation = personFlux -> personFlux
                .filter(person -> person.getAge() > 18)
                .doOnNext(person -> person.setName(person.getName().toUpperCase(Locale.ROOT)))
                .doOnDiscard(Person.class, person -> System.out.println("I was discarded! -> " + person));

        getPeople()
                .transform(personTransformation)
                .subscribe(Utils.getDefaultSubscriber());

    }

    private static Flux<Person> getPeople() {
        return Flux.range(1, 10)
                   .map(i -> new Person());
    }
}
