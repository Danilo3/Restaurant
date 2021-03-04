package ru.koryakin.dacha2.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionUtilService {
    
    public boolean addSessionAttribute(HttpSession session, String name, Object obj) {
        Object attribute =  session.getAttribute(name);
        if (attribute == null) {
                session.setAttribute(name, obj);
        }
        return true;
    }
}
