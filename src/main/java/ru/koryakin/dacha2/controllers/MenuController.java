package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
public class MenuController {

    private static final String MENU_FILE_NAME = "src/main/resources/static/pdf/menu.pdf";

    @GetMapping(value = "/menu.html")
    public String menu() {
        return "menu";
    }

    @GetMapping(value = "/menu/download/")
    public ResponseEntity<byte[]> download() {
        ResponseEntity<byte[]> response;
        try {
            InputStream is = FileUtils.openInputStream(new File(MENU_FILE_NAME));
            byte[] contents = IOUtils.toByteArray(is);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = "menu.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        } catch (IOException ex) {
            log.warn("Error writing file to output stream");
            throw new RuntimeException("IOError writing file to output stream");
        }
        return response;
    }


}
