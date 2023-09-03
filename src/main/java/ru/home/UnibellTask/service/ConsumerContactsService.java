package ru.home.UnibellTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.UnibellTask.dto.ConsumerContactsDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;
import ru.home.UnibellTask.repository.ConsumerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ConsumerContactsService {

    private final ConsumerRepository consumerRepository;

    public ConsumerContactsDTO getAll(long id) {

        ConsumerEntity consumer = consumerRepository.findById(id).get();

        Map<String, List<String>> contactsConsumer = new HashMap<>();
        List<String> contactsEmails = new ArrayList<>();
        List<String> contactsPhones = new ArrayList<>();

        List<EmailAddressEntity> emails = consumer.getEmails();
        List<PhoneNumberEntity> phones = consumer.getPhones();

        for (EmailAddressEntity email : emails) {
            contactsEmails.add(email.getEmailAddress());
        }
        contactsConsumer.put("emails", contactsEmails);

        for (PhoneNumberEntity phone : phones) {
            contactsPhones.add(String.valueOf(phone.getPhoneNumber()));
        }
        contactsConsumer.put("phones", contactsPhones);

        return ConsumerContactsDTO.builder()
                .name(consumer.getName())
                .contacts(contactsConsumer)
                .build();
    }
}
