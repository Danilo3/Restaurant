package ru.koryakin.dacha2.services.impl;

import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.services.SessionUtilService;

import javax.servlet.http.HttpSession;

@Service
public class SessionUtilServiceImpl implements SessionUtilService {
    
    public boolean addSessionAttribute(HttpSession session, String name, Object obj) {
        Object attribute = session.getAttribute(name);
        if (attribute == null) {
                session.setAttribute(name, obj);
        }
        return true;
    }
}
