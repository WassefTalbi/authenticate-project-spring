package yaz.market.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yaz.market.ecommerce.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
