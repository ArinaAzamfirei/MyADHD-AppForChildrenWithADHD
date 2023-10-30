package my.adhd.MyADHD.api.repositories;

import my.adhd.MyADHD.models.GameResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameResults, Integer> {
}
