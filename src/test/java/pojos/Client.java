package pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private String firm_name;
    private String firm_town;
    private String firm_addr;
    private String firm_is_reg_vat;
    private String firm_mol;
}
