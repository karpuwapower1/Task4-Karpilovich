package by.task4.karpilovich.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import by.task4.karpilovich.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}