package by.task4.karpilovich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.task4.karpilovich.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}