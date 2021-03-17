package ru.koryakin.dacha2.services;

import javax.servlet.http.HttpSession;

public interface SessionUtilService {

    boolean addSessionAttribute(HttpSession session, String name, Object obj);
}
