package ru.koryakin.dacha2.services;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
