package ru.home.UnibellTask.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.UnibellTask.entity.ConsumerEntity;

import java.util.List;

@Repository
public interface ConsumerRepository extends CrudRepository<ConsumerEntity, Long> {

    List<ConsumerEntity> findAll();
}
