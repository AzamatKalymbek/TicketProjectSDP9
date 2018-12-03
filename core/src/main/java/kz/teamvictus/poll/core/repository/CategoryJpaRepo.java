package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryJpaRepo extends JpaRepository<Category, Long> {

}
