package my.adhd.MyADHD.api.repositories;


import my.adhd.MyADHD.models.Child;
import my.adhd.MyADHD.models.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestsRepository extends JpaRepository<Tests, Integer> {

    List<Tests> findAllByChildId(Child childId);
}
