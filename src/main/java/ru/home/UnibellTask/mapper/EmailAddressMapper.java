package ru.home.UnibellTask.mapper;

import org.springframework.stereotype.Component;
import ru.home.UnibellTask.dto.EmailAddressDTO;
import ru.home.UnibellTask.entity.EmailAddressEntity;

@Component
public class EmailAddressMapper {

    public EmailAddressEntity toEntity(EmailAddressDTO emailAddressDTO) {
        return EmailAddressEntity.builder()
                .emailAddress(emailAddressDTO.getEmailAddress())
                .build();
    }

}
