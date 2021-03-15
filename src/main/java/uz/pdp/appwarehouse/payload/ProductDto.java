package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer CategoryId;
    private Integer photoId;
    private Integer measurementId;
}
