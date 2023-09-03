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

@AllArgsConstructor
@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final EmailAddressRepository emailAddressRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    //Сохраняем клиента, после сразу почту с привязкой к клиенту, телефон.
    public void save(ConsumerEntity consumer) {
        ConsumerEntity saveConsumer = consumerRepository.save(consumer);

        List<EmailAddressEntity> emails = saveConsumer.getEmails();
        EmailAddressEntity emailEntity = emails.stream().findFirst().get();
        emailEntity.setConsumerEntity(saveConsumer);
        emailAddressRepository.save(emailEntity);

        List<PhoneNumberEntity> phones = saveConsumer.getPhones();
        PhoneNumberEntity phoneEntity = phones.stream().findFirst().get();
        phoneEntity.setConsumerEntity(saveConsumer);
        phoneNumberRepository.save(phoneEntity);
    }

    public ConsumerEntity get(long id) {
        ConsumerEntity consumer = consumerRepository.findById(id).get();
        System.out.println();
        return consumer;
    }

    public List<ConsumerEntity> getAll() {
        return consumerRepository.findAll();
    }

}
