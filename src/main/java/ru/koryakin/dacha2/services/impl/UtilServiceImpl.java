package ru.koryakin.dacha2.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.koryakin.dacha2.services.UtilService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Service
public class UtilServiceImpl implements UtilService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public <T> Page<T> makePageImpl(ArrayList<T> itemsArrayList, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<T> list;
        if (itemsArrayList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, itemsArrayList.size());
            list = itemsArrayList.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), itemsArrayList.size());
    }

    public LocalDate convertToLocalDateViaMillisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public <T> ArrayList<T> arrayListFromIterable(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        ArrayList<T> items = new ArrayList<>();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        return items;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String arrayToStr(ArrayList<String> strings) {
        return strings.toString().replace("{", "").replace("}", "");
    }


    public ArrayList<String> findAllImagesInDirectory(String imagePrefix) {
        Iterator<File> iterator = FileUtils.iterateFiles(new File(imagePrefix), new String[]{"png", "jpg", "jpeg", "svg"}, true);
        ArrayList<String> imagesUrls = new ArrayList<>();
        while (iterator.hasNext()) {
            File next = iterator.next();
            Path path = Path.of(next.getPath());
            imagesUrls.add(imagePrefix + path.getFileName());
        }
        return imagesUrls;
    }

    public void uploadFile(MultipartFile file, Model model, String filename) {
        boolean error = true;
        if (!file.isEmpty()) {
            try {
                FileUtils.deleteQuietly(new File(filename));
                File newFile = new File(filename);
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(bytes);
                stream.close();
                error = false;
                log.info("Upload new " + filename + " successfully");
            } catch (Exception e) {
                log.info("Failed to upload file:" + e.getMessage());
            }
        }
        model.addAttribute("error", error);
    }
}
