package ru.home.UnibellTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class PhoneNumberDTO implements Contact {

    private long id;
    private long phoneNumber;

}
