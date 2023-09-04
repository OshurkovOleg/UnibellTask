package ru.home.UnibellTask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "consumers")
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "full_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumerEntity")
    private List<PhoneNumberEntity> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumerEntity")
    private List<EmailAddressEntity> emails;

}
