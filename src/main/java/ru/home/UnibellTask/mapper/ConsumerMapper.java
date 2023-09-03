package ru.home.UnibellTask.mapper;

import org.springframework.stereotype.Component;
import ru.home.UnibellTask.dto.ConsumerDTO;
import ru.home.UnibellTask.entity.ConsumerEntity;
import ru.home.UnibellTask.entity.EmailAddressEntity;
import ru.home.UnibellTask.entity.PhoneNumberEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerMapper {
    public ConsumerDTO toDTO(ConsumerEntity consumerEntity) {
        return ConsumerDTO.builder()
                .name(consumerEntity.getName())
                .email(consumerEntity.getEmails().stream()
                        .findFirst().get()
                        .getEmailAddress())
                .phone(consumerEntity.getPhones().stream()
                        .findFirst().get()
                        .getPhoneNumber())
                .build();
    }

    public ConsumerEntity toEntity(ConsumerDTO consumerDTO) {

        List<PhoneNumberEntity> phonesEntity = new ArrayList<>();
        phonesEntity.add(PhoneNumberEntity.builder()
                .phoneNumber(consumerDTO.getPhone())
                .build());

        List<EmailAddressEntity> emailsEntity = new ArrayList<>();
        emailsEntity.add(EmailAddressEntity.builder()
                .emailAddress(consumerDTO.getEmail())
                .build());

        return ConsumerEntity.builder()
                .name(consumerDTO.getName())
                .emails(emailsEntity)
                .phones(phonesEntity)
                .build();
    }
}
