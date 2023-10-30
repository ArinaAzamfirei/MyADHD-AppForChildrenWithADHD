package my.adhd.MyADHD.api.repositories;


import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Integer> {


    List<Child> findAllByUserId(User userId);

    Optional<Child> findChildByCode(String code);
}
