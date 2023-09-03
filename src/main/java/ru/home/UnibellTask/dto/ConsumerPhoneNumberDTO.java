package ru.home.UnibellTask.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumerPhoneNumberDTO implements Contact {

    String name;
    List<Long> phones;

}
