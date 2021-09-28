package pl.familiamed.FamiliaNET.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.familiamed.FamiliaNET.model.User;
import pl.familiamed.FamiliaNET.services.UserService;

@RestController
@RequestMapping("v1/login")
@CrossOrigin("*")
@AllArgsConstructor
public class LoginController {

  private final UserService userService;

  @PostMapping
  public User login (String login , String password){
    User user = null;
    try {
      user = userService.findByLogin(login);
    } catch (DataAccessException e){
      throw  new DataAccessException("Niepoprawny login" , e) {
      };
    }
    if (login == null) {
      throw new DataAccessException("Nie znaleziono użytkownika") {
      };
    }else{
      if( password.equals(user.getPassword())){
        return user;
      }else {
        throw new DataAccessException("Nieprawidłowe hasło"){};
      }
    }
  }
}
