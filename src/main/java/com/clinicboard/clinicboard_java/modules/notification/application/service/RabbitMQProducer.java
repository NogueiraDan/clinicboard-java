package com.clinicboard.clinicboard_java.modules.notification.application.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentRequestDto;
import com.clinicboard.clinicboard_java.modules.notification.api.contract.MessagingInterface;

@Component
public class RabbitMQProducer implements MessagingInterface {

    final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${broker.queue.notification.name}")
    private String routingKey;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void publishNotification(AppointmentRequestDto appointment) {
        System.out.println("Mensagem produzida: " + appointment);
        rabbitTemplate.convertAndSend(routingKey, appointment);
    }

}
