package ru.home.UnibellTask.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmailAddressDTO implements Contact {

    private long id;
    private String emailAddress;

}
