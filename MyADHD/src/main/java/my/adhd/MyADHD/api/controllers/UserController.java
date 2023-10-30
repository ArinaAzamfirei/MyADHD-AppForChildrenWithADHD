package my.adhd.MyADHD.api.controllers;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.repositories.UserRepository;
import my.adhd.MyADHD.models.User;
import my.adhd.MyADHD.api.services.UserService;
import my.adhd.MyADHD.models.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){return ResponseEntity.ok(userService.findAll());}

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDto);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody User user){
        userService.update(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

}
