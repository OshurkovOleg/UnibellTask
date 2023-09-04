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
import java.util.NoSuchElementException;

import static ru.home.UnibellTask.constant.Constants.CONSUMER_NOT_FOUND;

@AllArgsConstructor
@Service
public class EmailAddressService {

    private final EmailAddressRepository emailAddressRepository;
    private final ConsumerRepository consumerRepository;

    public void save(long consumerID, EmailAddressEntity email) {
        email.setConsumerEntity(getConsumer(consumerID));
        emailAddressRepository.save(email);
    }

    public ConsumerEmailAddressDTO get(long consumerID) {
        List<String> emailsConsumer = new ArrayList<>();
        ConsumerEntity consumer = getConsumer(consumerID);
        consumer.getEmails().forEach(email -> emailsConsumer.add(email.getEmailAddress()));

        return ConsumerEmailAddressDTO.builder()
                .name(consumer.getName())
                .emails(emailsConsumer)
                .build();
    }

    private ConsumerEntity getConsumer(long consumerID) {
        return consumerRepository.findById(consumerID)
                .orElseThrow(() -> new NoSuchElementException(CONSUMER_NOT_FOUND));
    }

}
