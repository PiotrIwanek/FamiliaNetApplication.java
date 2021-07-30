package pl.familiamed.FamiliaNET.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.User;
import pl.familiamed.FamiliaNET.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public User create(User user){
        return  userRepository.save(user);
    }

    public List<User> getAll (){
        log.info("Pobrano wszystkich użytkowników");
        return userRepository.findAll();
    }

    public  User getById (Long id ){
        log.info("Pobrano użytkownika nr: " + id);
        return userRepository.findById(id)
                                    .orElseThrow( ()->  new IllegalArgumentException(String.format("Couldn't find user with id", id)));
    }

    public User update (Long id, User user){
        final  User userToUpdate = userRepository.findById(id)
                .orElseThrow( ()->  new IllegalArgumentException(String.format("Couldn't find user with id", id)));
        userToUpdate.setName(user.getName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        return userRepository.save(userToUpdate);
    }

    public void delete (Long id){
        User user = userRepository.findById(id)
                .orElseThrow( ()->  new IllegalArgumentException(String.format("Couldn't find user with id", id)));
        userRepository.delete(user);
    }
}

