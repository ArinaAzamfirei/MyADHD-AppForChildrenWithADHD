package my.adhd.MyADHD.api.services;

import lombok.RequiredArgsConstructor;
import my.adhd.MyADHD.api.repositories.UserRepository;
import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.api.repositories.ChildRepository;
import my.adhd.MyADHD.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChildService {


    private final ChildRepository childRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TestsService testsService;
    public List<Child> findAll(){return childRepository.findAll();}

    public Child findById(Integer childId){
        return childRepository.findById(childId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



    public List<Child> getChildByUserId(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return childRepository.findAllByUserId(user);
    }

    public Child create(Child child, Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        child.setUserId(user);
//        var childEncoded = Child.builder()
//                .firstname(Base64.getEncoder().encodeToString(child.getFirstname().getBytes()))
//                .lastname(Base64.getEncoder().encodeToString(child.getLastname().getBytes()))
//                .nickname(child.getNickname())
//                .age(child.getAge())
//                .gender(child.getGender())
//                .code(child.getCode())
//                .userId(user).build();
        return childRepository.save(child);
    }
    public Child update(final Integer id, Child child){
        Child childFromDb = childRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        childFromDb.setFirstname(child.getFirstname());
        childFromDb.setLastname(child.getLastname());
        childFromDb.setNickname(child.getNickname());
        childFromDb.setAge(child.getAge());
        childFromDb.setGender(child.getGender());
        childFromDb.setCode(child.getCode());
        return childRepository.save(childFromDb);
    }

    public void delete(Integer id){
        testsService.deleteTestByChildId(id);
        childRepository.deleteById(id);}
}
