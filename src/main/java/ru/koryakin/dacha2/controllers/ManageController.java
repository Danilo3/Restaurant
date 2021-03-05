package ru.koryakin.dacha2.controllers;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.repositories.DachaUserRepository;
import ru.koryakin.dacha2.repositories.MenuItemRepository;
import ru.koryakin.dacha2.repositories.TableBookingRepository;
import ru.koryakin.dacha2.repositories.UserMessageRepository;
import ru.koryakin.dacha2.services.SaveMenuFromPDFService;
import ru.koryakin.dacha2.services.SessionUtilService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;


@Controller
public class ManageController {

    private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private DachaUserRepository dachaUserRepository;

    @Autowired
    SessionUtilService sessionUtilService;

    @GetMapping(value = "/manage.html")
    public String manage(HttpServletRequest request, HttpSession session, Model model){
        String username = request.getUserPrincipal().getName();
        model.addAttribute("name", username);
        String imgUrl = dachaUserRepository.findByUsername(username).getAvatarUrl();
        model.addAttribute("imgUrl", imgUrl);
        sessionUtilService.addSessionAttribute(session, "name", username);
        sessionUtilService.addSessionAttribute(session, "imgUrl", imgUrl);
        return "manage";
    }

    @GetMapping(value = "/manage")
    @ResponseBody
    public String manageAPI() {
        return HttpStatus.OK.toString();
    }

    @GetMapping(value = "/manage/getMessages/all")
    public @ResponseBody Iterable<UserMessage> getAllMessages() {
        return userMessageRepository.findAll();
    }

    @RequestMapping(value = "/manage/messages/")
    public String manageMessages(HttpSession session, Model model){
        model.addAttribute("msgList", userMessageRepository.findAll());
        addUserDataFromSession(session, model);
        return "messages.html";
    }

    @DeleteMapping("/manage/messages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserMessage(@PathVariable Integer id){
        Optional<UserMessage> message = userMessageRepository.findById(id);
        userMessageRepository.deleteById(id);
        message.ifPresent(userMessage -> logger.info("[DELETE] Message: " + userMessage));
    }


    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/manage/menu/")
    public String menuPage(Model model, HttpSession session){
        model.addAttribute("menuList", menuItemRepository.findAll());
        addUserDataFromSession(session, model);
        return "upload-menu";
    }

    @Value("${menu.path}")
    private String menuPath;

    @PostMapping("/manage/menu")
    public String uploadMenu(@RequestParam("file") MultipartFile file, HttpSession session, Model model){
        uploadFile(file, model, menuPath);
        addUserDataFromSession(session, model);
        return "upload-menu";
    }

    @Value("${wine.path}")
    private String wineMenuPath;

    @PostMapping("/manage/wine")
    public String uploadWineMenu(@RequestParam("file") MultipartFile file, HttpSession session, Model model){
        uploadFile(file, model, wineMenuPath);
        addUserDataFromSession(session, model);
        return "upload-menu";
    }


    private static void addUserDataFromSession(HttpSession session, Model model) {
        model.addAttribute("imgUrl", session.getAttribute("imgUrl"));
        model.addAttribute("name", session.getAttribute("name"));
    }

    private void uploadFile(MultipartFile file, Model model, String filename) {
        boolean error = true;
        if (!file.isEmpty()) {
            try {
                FileUtils.deleteQuietly(new File(filename));
                File newMenu = new File(filename);
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newMenu));
                stream.write(bytes);
                stream.close();
                error = false;
                logger.info("Upload new menu successfully");
            } catch (Exception e) {
                logger.info("Failed to upload file:" + e.getMessage());
            }
        }
        model.addAttribute("error", error);
    }

    @Autowired
    private SaveMenuFromPDFService saveMenuFromPDFService;

    @GetMapping(value = "/manage/updateMenu")
    public String updateMenu(Model model, HttpServletResponse response){
        saveMenuFromPDFService.save();
        response.setHeader("Location", "menu.html");
        return "redirect:" + "/menu";
    }

    @Autowired
    private TableBookingRepository tableBookingRepository;

    @GetMapping("/manage/booking/")
    public String bookingPage(Model model, HttpSession session){
        model.addAttribute("bookingList", tableBookingRepository.findAll());
        addUserDataFromSession(session, model);
        return "manage-booking";
    }

    @GetMapping("/manage/blog/")
    public String blogPage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        return "manage-blog";
    }

}
