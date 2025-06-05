package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByAuthority(String authority);

}
