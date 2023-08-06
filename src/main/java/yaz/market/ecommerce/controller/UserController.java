package yaz.market.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yaz.market.ecommerce.exception.NotFoundExecption;
import yaz.market.ecommerce.model.User;
import yaz.market.ecommerce.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId)  {
        try {
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }catch (NotFoundExecption e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/test")
    public ResponseEntity<String> test()  {
      System.out.println("test tsest $$$$$$$$$$$$$");
            return new ResponseEntity<>("test test", HttpStatus.OK);


    }


}
