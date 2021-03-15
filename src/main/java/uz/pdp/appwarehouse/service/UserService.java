package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseService warehouseService;

    public Page<User> page(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public User getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public Result delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("User not found !",false);
        User user = optionalUser.get();
        userRepository.delete(user);
        return new Result("User deleted !",true);
    }


    public User add(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (!optionalUser.isPresent()) {
            UUID uuid = UUID.randomUUID();
            User user = new User();
            user.setActive(userDto.isStatus());
            user.setCode(uuid.toString());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());

            Set<Warehouse> warehouseSet = new HashSet<>();
            if (userDto.getWareHouseId() != null) {
                for (Integer integer : userDto.getWareHouseId()) {
                    Optional<Warehouse> warehouseOptional = warehouseService.warehouseRepository.findById(integer);
                    warehouseOptional.ifPresent(warehouseSet::add);
                }
            }

            user.setWarehouses(warehouseSet);
            return userRepository.save(user);
        }
        return new User();
    }
}
