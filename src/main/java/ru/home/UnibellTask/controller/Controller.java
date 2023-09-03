package ru.home.UnibellTask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.home.UnibellTask.dto.*;
import ru.home.UnibellTask.mapper.ConsumerMapper;
import ru.home.UnibellTask.mapper.EmailAddressMapper;
import ru.home.UnibellTask.mapper.PhoneNumberMapper;
import ru.home.UnibellTask.service.ConsumerContactsService;
import ru.home.UnibellTask.service.ConsumerService;
import ru.home.UnibellTask.service.EmailAddressService;
import ru.home.UnibellTask.service.PhoneNumberService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    ConsumerService consumerService;
    ConsumerMapper consumerMapper;
    PhoneNumberService phoneNumberService;
    PhoneNumberMapper phoneNumberMapper;
    EmailAddressService emailAddressService;
    EmailAddressMapper emailAddressMapper;
    ConsumerContactsService consumerContactsService;


    //Добавление нового клиента
    @PostMapping("/consumers")
    public ResponseEntity<HttpStatus> saveConsumer(@RequestBody ConsumerDTO consumerDTO) {
        consumerService.save(consumerMapper.toEntity(consumerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Добавление нового телефона клиенту
    @PostMapping("/consumers/phone")
    public ResponseEntity<HttpStatus> savePhoneConsumer(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        phoneNumberService.save(phoneNumberDTO.getId(), phoneNumberMapper.toEntity(phoneNumberDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Добавление новой почты клиента
    @PostMapping("/consumers/email")
    public ResponseEntity<HttpStatus> saveEmailAddress(@RequestBody EmailAddressDTO emailAddressDTO) {
        emailAddressService.save(emailAddressDTO.getId(), emailAddressMapper.toEntity(emailAddressDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Получение информации о клиенте по id
    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerDTO> getConsumer(@PathVariable long id) {
        return ResponseEntity.ok(consumerMapper.toDTO(consumerService.get(id)));
    }

    // Получение всего списка клиентов
    @GetMapping("/consumers/all")
    public ResponseEntity<List<ConsumerDTO>> getAllConsumer() {
        return ResponseEntity.ok(consumerService.getAll().stream()
                .map(consumerEntity -> consumerMapper.toDTO(consumerEntity))
                .toList());
    }

    // Получение списка контактов заданного клиента
    @GetMapping("/consumers/contacts/{id}")
    public ResponseEntity<ConsumerContactsDTO> getContactsConsumer(@PathVariable long id) {
        return ResponseEntity.ok(consumerContactsService.getAll(id));
    }


    //Получение списка контактов заданного типа(почта или номер телефона) заданного клиента
    @GetMapping("/consumers/contacts/{type}/{id}")
    public ResponseEntity<Contact> getTypeContactsConsumer(@PathVariable String type, @PathVariable long id) {
        if (type.equals("phone")) {
            return ResponseEntity.ok(phoneNumberService.get(id));
        } else if (type.equals("email")) {
            return ResponseEntity.ok(emailAddressService.get(id));
        }
        return null;
    }


}
