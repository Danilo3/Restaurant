package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.repositories.*;
import ru.koryakin.dacha2.services.*;
import ru.koryakin.dacha2.services.impl.BlogPostServiceImpl;
import ru.koryakin.dacha2.services.SaveMenuFromPDFService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collections;


@Slf4j
@Controller
public class ManageController {

    @Value("${image.prefix}")
    private String imagePrefix;

    @Value("${lunch.path}")
    private String lunchMenuPath;

    @Value("${wine.path}")
    private String wineMenuPath;

    @Value("${menu.path}")
    private String menuPath;

    private final UtilService utilService;

    private final DachaUserService dachaUserService;

    private final BlogPostService blogPostService;

    private final SessionUtilService sessionUtilService;

    private final MenuService menuService;

    private final SaveMenuFromPDFService saveMenuFromPDFService;

    private final TableBookingService tableBookingService;

    private final PostTagService postTagService;

    @Autowired
    public ManageController(UtilService utilService, DachaUserService dachaUserService, BlogPostService blogPostService,
                            SessionUtilService sessionUtilService, MenuService menuService, SaveMenuFromPDFService saveMenuFromPDFService,
                            TableBookingService tableBookingService, PostTagService postTagService) {
        this.utilService = utilService;
        this.dachaUserService = dachaUserService;
        this.blogPostService = blogPostService;
        this.sessionUtilService = sessionUtilService;
        this.menuService = menuService;
        this.saveMenuFromPDFService = saveMenuFromPDFService;
        this.tableBookingService = tableBookingService;
        this.postTagService = postTagService;
    }

    @GetMapping(value = {"/manage.html", "/manage"})
    public String manage(HttpServletRequest request, HttpSession session, Model model){
        String username = request.getUserPrincipal().getName();
        String imgUrl = dachaUserService.findByUsername(username).getAvatarUrl();
        sessionUtilService.addSessionAttribute(session, "name", username);
        sessionUtilService.addSessionAttribute(session, "imgUrl", imgUrl);
        addUserDataFromSession(session, model);
        return "manage";
    }

    @GetMapping("/manage/menu/")
    public String menuPage(Model model, HttpSession session){
        model.addAttribute("menuList", menuService.findAll());
        addUserDataFromSession(session, model);
        return "manage-menu";
    }

    @PostMapping("/manage/menu")
    public String uploadMenu(@RequestParam("file") MultipartFile file, HttpSession session, Model model){
        utilService.uploadFile(file, model, menuPath);
        addUserDataFromSession(session, model);
        return "manage-menu";
    }

    @PostMapping("/manage/wine")
    public String uploadWineMenu(@RequestParam("file") MultipartFile file, HttpSession session, Model model){
        utilService.uploadFile(file, model, wineMenuPath);
        addUserDataFromSession(session, model);
        return "manage-menu";
    }

    @PostMapping("/manage/lunch")
    public String uploadLunchMenu(@RequestParam("file") MultipartFile file, HttpSession session, Model model){
        utilService.uploadFile(file, model, lunchMenuPath);
        addUserDataFromSession(session, model);
        return "manage-menu";
    }

    @GetMapping(value = "/manage/updateMenu")
    public String updateMenu(HttpServletResponse response){
        saveMenuFromPDFService.save();
        response.setHeader("Location", "menu.html");
        return "redirect:" + "/menu";
    }

    @GetMapping("/manage/booking/")
    public String bookingPage(Model model, HttpSession session){
        model.addAttribute("bookingList", tableBookingService.findAll());
        addUserDataFromSession(session, model);
        return "manage-booking";
    }

    @GetMapping("/manage/blog/")
    public String blogPage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        model.addAttribute("post", new BlogPost());
        return "manage-blog";
    }

    @GetMapping("/manage/blog/new-post/")
    public String newPostPage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        model.addAttribute("tags", postTagService.findAll());
        model.addAttribute("post", new BlogPost());
        model.addAttribute("images", utilService.findAllImagesInDirectory(imagePrefix));
        return "new-post";
    }

    @GetMapping("/manage/blog/edit-post/")
    public String editPostPage(@RequestParam(name = "title", required = false) String title, Model model, HttpSession session){
        addUserDataFromSession(session, model);
        if (title == null || title.isEmpty()) {
            model.addAttribute("titles", blogPostService.findAllTitle());
            model.addAttribute("post", new BlogPost());
        } else {
            model.addAttribute("titles", Collections.singletonList(title));
            model.addAttribute("post", blogPostService.findByTitle(title));
        }
        return "edit-post";
    }

    @GetMapping("/manage/image/")
    public String uploadImagePage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        model.addAttribute("post", new BlogPost());
        return "add-image";
    }

    @PostMapping("/manage/image/")
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, HttpSession session, Model model){
        if (file == null || name == null || name.isEmpty()) {
            model.addAttribute("error", true);
            return "add-image";
        }
        name = imagePrefix + name;
        utilService.uploadFile(file, model, name);
        addUserDataFromSession(session, model);
        model.addAttribute("error", false);
        return "add-image";
    }

    @GetMapping("/manage/emails/")
    public String emailsPage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        return "manage-emails";
    }

    @GetMapping("/manage/gallery/")
    public String galleryPage(Model model, HttpSession session){
        addUserDataFromSession(session, model);
        model.addAttribute("images", utilService.arrayToStr(utilService.findAllImagesInDirectory(imagePrefix)));
        return "manage-gallery";
    }

    @GetMapping("/manage/orders/")
    public String ordersPage(Model model, HttpSession session) {
        addUserDataFromSession(session, model);
        return "manage-orders";
    }

    @GetMapping("/manage/messages/")
    public String messagesPage(Model model, HttpSession session) {
        addUserDataFromSession(session, model);
        return "manage-messages";
    }

    private static void addUserDataFromSession(HttpSession session, Model model) {
        model.addAttribute("imgUrl", session.getAttribute("imgUrl"));
        model.addAttribute("name", session.getAttribute("name"));
    }
}
