package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Integer[] wareHouseId;
    private boolean status;
    private Long chatId;
}
