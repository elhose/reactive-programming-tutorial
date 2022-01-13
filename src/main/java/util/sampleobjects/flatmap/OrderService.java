package util.sampleobjects.flatmap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static Map<Integer, List<PurchaseOrder>> db = Map.of(
            1, List.of(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)),
            2, List.of(new PurchaseOrder(2), new PurchaseOrder(2)));

    public static Flux<PurchaseOrder> getOrders(int orderId) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
                       db.get(orderId).forEach(purchaseOrderFluxSink::next);
                       purchaseOrderFluxSink.complete();
                   })
                   .delayElements(Duration.ofMillis(500));
    }

}

