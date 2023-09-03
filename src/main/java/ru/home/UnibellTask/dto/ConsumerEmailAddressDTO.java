package ru.home.UnibellTask.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumerEmailAddressDTO implements Contact{
    String name;
    List<String> emails;
}
