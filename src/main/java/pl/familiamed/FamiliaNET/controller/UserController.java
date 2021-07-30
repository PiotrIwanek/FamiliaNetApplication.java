package pl.familiamed.FamiliaNET.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.familiamed.FamiliaNET.model.User;
import pl.familiamed.FamiliaNET.services.UserService;

import java.util.List;

@RestController
@RequestMapping("v1/user")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAll (){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));}


    @PostMapping
    public ResponseEntity<User> create (@RequestBody User user){
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update (@PathVariable Long id , @RequestBody User user){
        return ResponseEntity.ok(userService.update(id , user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
