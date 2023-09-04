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
import java.util.NoSuchElementException;

import static ru.home.UnibellTask.constant.Constants.CONSUMER_NOT_FOUND;

@AllArgsConstructor
@Service
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final ConsumerRepository consumerRepository;

    public void save(long consumerID, PhoneNumberEntity phone) {
        phone.setConsumerEntity(getConsumer(consumerID));
        phoneNumberRepository.save(phone);
    }

    public ConsumerPhoneNumberDTO get(long consumerID) {
        List<Long> phonesConsumer = new ArrayList<>();
        ConsumerEntity consumer = getConsumer(consumerID);
        consumer.getPhones().forEach(phone -> phonesConsumer.add(phone.getPhoneNumber()));

        return ConsumerPhoneNumberDTO.builder()
                .name(consumer.getName())
                .phones(phonesConsumer)
                .build();
    }

    private ConsumerEntity getConsumer(long consumerID) {
        return consumerRepository.findById(consumerID)
                .orElseThrow(() -> new NoSuchElementException(CONSUMER_NOT_FOUND));
    }

}
