package ru.home.UnibellTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;
import ru.home.UnibellTask.repository.ConsumerRepository;
import ru.home.UnibellTask.repository.EmailAddressRepository;
import ru.home.UnibellTask.repository.PhoneNumberRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static ru.home.UnibellTask.constant.Constants.*;

@AllArgsConstructor
@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final EmailAddressRepository emailAddressRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public void save(ConsumerEntity consumerEntity) {
        ConsumerEntity consumer = consumerRepository.save(consumerEntity);
        saveEmail(consumer);
        savePhone(consumer);
    }

    private void savePhone(ConsumerEntity consumer) {
        List<PhoneNumberEntity> phones = consumer.getPhones();
        PhoneNumberEntity phoneEntity = phones.stream()
                .findFirst().orElseThrow(() -> new NoSuchElementException(PHONE_NOT_FOUND));
        phoneEntity.setConsumerEntity(consumer);
        phoneNumberRepository.save(phoneEntity);
    }

    private void saveEmail(ConsumerEntity consumer) {
        EmailAddressEntity emailEntity = consumer.getEmails().stream()
                .findFirst().orElseThrow(() -> new NoSuchElementException(EMAIL_NOT_FOUND));
        emailEntity.setConsumerEntity(consumer);
        emailAddressRepository.save(emailEntity);
    }

    public ConsumerEntity get(long id) {
        return getConsumer(id);
    }

    private ConsumerEntity getConsumer(long id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(CONSUMER_NOT_FOUND));
    }

    public List<ConsumerEntity> getAll() {
        return consumerRepository.findAll();
    }

}
