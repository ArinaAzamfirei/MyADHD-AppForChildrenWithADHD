package my.adhd.MyADHD.api.repositories;


import my.adhd.MyADHD.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
