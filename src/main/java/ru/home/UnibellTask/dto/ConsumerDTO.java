package ru.home.UnibellTask.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumerDTO {

    private String name;
    private String email;
    private long phone;

}
