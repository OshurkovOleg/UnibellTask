package ru.home.UnibellTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.UnibellTask.dto.ConsumerPhoneNumberDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;
import ru.home.UnibellTask.repository.ConsumerRepository;
import ru.home.UnibellTask.repository.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final ConsumerRepository consumerRepository;

    public void save(long consumerID, PhoneNumberEntity phone) {
        phone.setConsumerEntity(consumerRepository.findById(consumerID).get());
        phoneNumberRepository.save(phone);
    }

    public ConsumerPhoneNumberDTO get(long id) {
        List<Long> phonesConsumer = new ArrayList<>();
        ConsumerEntity consumer = consumerRepository.findById(id).get();
        consumer.getPhones().forEach(phone -> phonesConsumer.add(phone.getPhoneNumber()));

        return ConsumerPhoneNumberDTO.builder()
                .name(consumer.getName())
                .phones(phonesConsumer)
                .build();
    }


}
