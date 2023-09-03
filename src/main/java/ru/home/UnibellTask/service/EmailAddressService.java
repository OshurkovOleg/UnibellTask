package ru.home.UnibellTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.UnibellTask.dto.ConsumerEmailAddressDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.repository.ConsumerRepository;
import ru.home.UnibellTask.repository.EmailAddressRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EmailAddressService {

    EmailAddressRepository emailAddressRepository;
    ConsumerRepository consumerRepository;

    public void save(long consumerID, EmailAddressEntity email) {
        ConsumerEntity consumer = consumerRepository.findById(consumerID).get();
        email.setConsumerEntity(consumer);
        emailAddressRepository.save(email);
    }

    public ConsumerEmailAddressDTO get(long id) {
        List<String> emailsConsumer = new ArrayList<>();
        ConsumerEntity consumer = consumerRepository.findById(id).get();
        consumer.getEmails().forEach(email -> emailsConsumer.add(email.getEmailAddress()));

        return ConsumerEmailAddressDTO.builder()
                .name(consumer.getName())
                .emails(emailsConsumer)
                .build();
    }

}
