package context;

import reactor.core.publisher.Mono;
import util.Utils;

public class ContextDemo {
    private static final String USER_KEY = "user";

    public static void main(String[] args) {
        getWelcomeMessage()
//                .contextWrite(Context.of(USER_KEY, "Ricky"))
                .contextWrite(context -> context.put(USER_KEY, Utils.getFAKER().name().fullName()))
                .subscribe(Utils.getDefaultSubscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey(USER_KEY)) {
                return Mono.just("Welcome " + contextView.get(USER_KEY));
            }
            return Mono.error(new RuntimeException("No user available!"));
        });
    }
}
