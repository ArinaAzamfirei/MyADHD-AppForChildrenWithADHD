package my.adhd.MyADHD.api.services;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.models.User;
import my.adhd.MyADHD.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll(){return userRepository.findAll();}

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User create(User user){
        if(validatePassword(user.getPassword()) && validateNickname(user.getUsername()) &&
                validateEmail(user.getEmail()) && validateName(user.getLastname()) && validateName(user.getFirstname())){
            return userRepository.save(user);
        }
      else{
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public User update(Integer id, User user){

        User userFromDb = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(user.getEmail() == null){
            userFromDb.setEmail(userFromDb.getEmail());
        }
        else{
            userFromDb.setEmail(user.getEmail());
        }

        if(user.getUsername() == null){
            userFromDb.setUsername(userFromDb.getUsername());
        }
        else{
            userFromDb.setUsername(user.getUsername());
        }


        if(user.getFirstname() == null){
            userFromDb.setFirstname(userFromDb.getFirstname());
        }
        else{
            userFromDb.setFirstname(user.getFirstname());
        }


        if(user.getLastname() == null){
            userFromDb.setFirstname(userFromDb.getLastname());
        }
        else{
            userFromDb.setLastname(user.getLastname());
        }

        if(user.getPassword() == null){
            userFromDb.setPassword(userFromDb.getPassword());
        }
        else{
            userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userFromDb.setChildUser(userFromDb.getChildUser());
        userFromDb.setRole(userFromDb.getRole());
        return userRepository.save(userFromDb);
    }

    public void delete(Integer id){userRepository.deleteById(id);}

    private boolean validateName(String lastname) {
        if (lastname.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateNickname(String nickname) {
        String checkSpaces = "Aw{1,20}z";
        if (nickname.isEmpty()) {
            return false;
        } else if (nickname.length() > 20) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateEmail(String email) {
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (email.isEmpty()) {
            return false;
        } else if (!email.matches(checkEmail)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword(String password) {
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (password.isEmpty()) {
            return false;
        } else if (!password.matches(checkPassword)) {
            return false;
        } else {
            return true;
        }
    }

}
