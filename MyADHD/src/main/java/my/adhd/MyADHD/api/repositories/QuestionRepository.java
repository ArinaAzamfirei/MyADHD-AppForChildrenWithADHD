package my.adhd.MyADHD.api.repositories;


import my.adhd.MyADHD.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
