package pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String name;
    private String price_for_quantity;
    private String quantity_unit;
}
