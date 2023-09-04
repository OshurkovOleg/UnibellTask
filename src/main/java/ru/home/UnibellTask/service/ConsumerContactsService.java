package ru.home.UnibellTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.UnibellTask.dto.ConsumerContactsDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;
import ru.home.UnibellTask.repository.ConsumerRepository;

import java.util.*;

import static ru.home.UnibellTask.constant.Constants.*;

@AllArgsConstructor
@Service
public class ConsumerContactsService {

    private final ConsumerRepository consumerRepository;

    public ConsumerContactsDTO getAll(long id) {

        Map<String, List<String>> contactsConsumer = new HashMap<>();
        ConsumerEntity consumer = getConsumer(id);

        List<String> emails = new ArrayList<>();
        fillListEmails(consumer, emails);
        contactsConsumer.put(EMAIL, emails);

        List<String> phones = new ArrayList<>();
        fillListPhones(consumer, phones);
        contactsConsumer.put(PHONE, phones);

        return ConsumerContactsDTO.builder()
                .name(consumer.getName())
                .contacts(contactsConsumer)
                .build();
    }

    private ConsumerEntity getConsumer(long id) {
        return consumerRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException(CONSUMER_NOT_FOUND));
    }

    private void fillListPhones(ConsumerEntity consumer, List<String> contactsPhones) {
        for (PhoneNumberEntity phone : consumer.getPhones()) {
            contactsPhones.add(String.valueOf(phone.getPhoneNumber()));
        }
    }

    private void fillListEmails(ConsumerEntity consumer, List<String> contactsEmails) {
        for (EmailAddressEntity email : consumer.getEmails()) {
            contactsEmails.add(email.getEmailAddress());
        }
    }

}
