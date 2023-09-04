package ru.home.UnibellTask.mapper;

import org.springframework.stereotype.Component;
import ru.home.UnibellTask.dto.ConsumerDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.home.UnibellTask.constant.Constants.EMAIL_NOT_FOUND;
import static ru.home.UnibellTask.constant.Constants.PHONE_NOT_FOUND;

@Component
public class ConsumerMapper {

    public ConsumerDTO toDTO(ConsumerEntity consumerEntity) {
        return ConsumerDTO.builder()
                .name(consumerEntity.getName())
                .email(getEmailAddress(consumerEntity))
                .phone(getPhoneNumber(consumerEntity))
                .build();
    }

    private long getPhoneNumber(ConsumerEntity consumerEntity) {
        return consumerEntity.getPhones().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(PHONE_NOT_FOUND))
                .getPhoneNumber();
    }

    private String getEmailAddress(ConsumerEntity consumerEntity) {
        return consumerEntity.getEmails().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(EMAIL_NOT_FOUND))
                .getEmailAddress();
    }

    public ConsumerEntity toEntity(ConsumerDTO consumerDTO) {

        List<EmailAddressEntity> emailsEntity = new ArrayList<>();
        emailsEntity.add(getEmailAddressEntity(consumerDTO));

        List<PhoneNumberEntity> phonesEntity = new ArrayList<>();
        phonesEntity.add(getPhoneNumberEntity(consumerDTO));

        return ConsumerEntity.builder()
                .name(consumerDTO.getName())
                .emails(emailsEntity)
                .phones(phonesEntity)
                .build();
    }

    private PhoneNumberEntity getPhoneNumberEntity(ConsumerDTO consumerDTO) {
        return PhoneNumberEntity.builder()
                .phoneNumber(consumerDTO.getPhone())
                .build();
    }

    private EmailAddressEntity getEmailAddressEntity(ConsumerDTO consumerDTO) {
        return EmailAddressEntity.builder()
                .emailAddress(consumerDTO.getEmail())
                .build();
    }

}
