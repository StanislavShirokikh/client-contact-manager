package ru.shirokikh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shirokikh.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
