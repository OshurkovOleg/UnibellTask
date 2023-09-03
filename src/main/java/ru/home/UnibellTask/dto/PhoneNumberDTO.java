package ru.home.UnibellTask.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PhoneNumberDTO implements Contact {

    private long id;
    private long phoneNumber;

}
