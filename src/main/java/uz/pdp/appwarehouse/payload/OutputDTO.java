package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class OutputDTO {
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private Integer facturNumber;
    private Date date;
}
