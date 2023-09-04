package ru.home.UnibellTask.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static ru.home.UnibellTask.constant.Constants.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    private final ConsumerService consumerService;
    private final ConsumerMapper consumerMapper;
    private final PhoneNumberService phoneNumberService;
    private final PhoneNumberMapper phoneNumberMapper;
    private final EmailAddressService emailAddressService;
    private final EmailAddressMapper emailAddressMapper;
    private final ConsumerContactsService consumerContactsService;
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @PostMapping("/consumers")
    public ResponseEntity<HttpStatus> saveConsumer(@RequestBody ConsumerDTO consumerDTO) {
        consumerService.save(consumerMapper.toEntity(consumerDTO));
        LOGGER.info(CLIENT_ADD_SUCCESSFULLY + consumerDTO.getName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/consumers/phone")
    public ResponseEntity<HttpStatus> savePhoneConsumer(@RequestBody PhoneNumberDTO phoneNumberDTO) {
        phoneNumberService.save(phoneNumberDTO.getId(), phoneNumberMapper.toEntity(phoneNumberDTO));
        LOGGER.info(PHONE_NUMBER_ADD_SUCCESSFULLY + phoneNumberDTO.getPhoneNumber());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/consumers/email")
    public ResponseEntity<HttpStatus> saveEmailAddress(@RequestBody EmailAddressDTO emailAddressDTO) {
        emailAddressService.save(emailAddressDTO.getId(), emailAddressMapper.toEntity(emailAddressDTO));
        LOGGER.info(MAIL_ADDRESS_ADD_SUCCESSFULLY + emailAddressDTO.getEmailAddress());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerDTO> getConsumer(@PathVariable long id) {
        LOGGER.info(REQUEST_CLIENT_ID);
        return ResponseEntity.ok(consumerMapper.toDTO(consumerService.get(id)));
    }

    @GetMapping("/consumers/all")
    public ResponseEntity<List<ConsumerDTO>> getAllConsumer() {
        LOGGER.info(REQUEST_ALL_CUSTOMERS);
        return ResponseEntity.ok(consumerService.getAll().stream()
                .map(consumerMapper::toDTO)
                .toList());
    }

    @GetMapping("/consumers/contacts/{id}")
    public ResponseEntity<ConsumerContactsDTO> getContactsConsumer(@PathVariable long id) {
        LOGGER.info(REQUEST_ALL_CONTACT_DATA_FOR_CLIENT);
        return ResponseEntity.ok(consumerContactsService.getAll(id));
    }

    @GetMapping("/consumers/contacts/{type}/{id}")
    public ResponseEntity<Contact> getTypeContactsConsumer(@PathVariable String type, @PathVariable long id) {
        LOGGER.info(REQUEST_CONTACT_DATA_CLIENT_ID_SPECIFIED_CONTACT_TYPE);
        if (type.equals(PHONE)) {
            return ResponseEntity.ok(phoneNumberService.get(id));
        } else if (type.equals(EMAIL)) {
            return ResponseEntity.ok(emailAddressService.get(id));
        }
        return new ResponseEntity<>(ConsumerContactsDTO.builder().build(), HttpStatus.NOT_FOUND);
    }

}
