package ru.home.UnibellTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.UnibellTask.entity.PhoneNumberEntity;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumberEntity, Long> {
}
