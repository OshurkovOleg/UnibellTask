package ru.home.UnibellTask.mapper;

import org.springframework.stereotype.Component;
import ru.home.UnibellTask.dto.PhoneNumberDTO;
import ru.home.UnibellTask.entity.PhoneNumberEntity;

@Component
public class PhoneNumberMapper {

    public PhoneNumberEntity toEntity(PhoneNumberDTO phoneNumberDTO) {
        return PhoneNumberEntity.builder()
                .phoneNumber(phoneNumberDTO.getPhoneNumber())
                .build();
    }

}
