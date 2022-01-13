package operators;

import util.Utils;
import util.sampleobjects.flatmap.OrderService;
import util.sampleobjects.flatmap.UserService;

import java.time.Duration;

public class FlatMap {
    public static void main(String[] args) {

        // FlatMap Doesn't guarantee proper order
        UserService.getUsers()
                   .flatMap(user -> OrderService.getOrders(user.getUserId()))
                   .subscribe(Utils.getDefaultSubscriber("FlatMap ->"));

        // ConcatMap guarantees proper order
        UserService.getUsers()
                   .delaySequence(Duration.ofSeconds(5))
                   .concatMap(user -> OrderService.getOrders(user.getUserId()))
                   .subscribe(Utils.getDefaultSubscriber("ConcatMap ->"));

        Utils.sleep(10);
    }
}
