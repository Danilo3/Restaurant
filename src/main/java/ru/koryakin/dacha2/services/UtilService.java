package ru.koryakin.dacha2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.koryakin.dacha2.domain.UserEmail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface UtilService {

    <T> Page<T> makePageImpl(ArrayList<T> itemsArrayList, Pageable pageable);

    LocalDate convertToLocalDateViaMillisecond(Date date);

    void uploadFile(MultipartFile file, Model model, String filename);

    ArrayList<String> findAllImagesInDirectory(String imagePrefix);

    String arrayToStr(ArrayList<String> list);

    <T> List<T> arrayListFromIterable(Iterable<T> iterable);

    String decodeToken(String token);

    String generateToken(String username);
}
