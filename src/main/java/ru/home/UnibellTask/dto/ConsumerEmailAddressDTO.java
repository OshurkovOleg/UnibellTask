package ru.home.UnibellTask.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumerEmailAddressDTO implements Contact {

    private String name;
    private List<String> emails;

}
