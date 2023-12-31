package ru.home.UnibellTask.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumerContactsDTO implements Contact {

    private String name;
    private Map<String, List<String>> contacts = new HashMap<>();

}
