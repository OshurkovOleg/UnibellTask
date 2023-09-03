package ru.home.UnibellTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.UnibellTask.entity.EmailAddressEntity;

@Repository
public interface EmailAddressRepository extends CrudRepository<EmailAddressEntity, Long> {
}
