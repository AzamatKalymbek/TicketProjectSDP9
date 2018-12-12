package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepo extends JpaRepository<Role, Long> {
    Role findRoleByCode(String code);
}
