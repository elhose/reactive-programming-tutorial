package util.sampleobjects.flatmap;

import lombok.Data;
import util.Utils;

@Data
public class PurchaseOrder {
    private String item;
    private String price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item = Utils.getFAKER().commerce().promotionCode();
        this.price = Utils.getFAKER().commerce().price();
    }
}
