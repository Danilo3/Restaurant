package ru.koryakin.dacha2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.koryakin.dacha2.domain.MenuItem;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import ru.koryakin.dacha2.repositories.MenuItemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
public class SaveMenuFromPDFService {

    @Value("${menu.path}")
    private String menuPath;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public boolean save(){
        ArrayList<MenuItem> menu = getAllFromPDFMenu();
        if (!menu.isEmpty()) {
            menuItemRepository.deleteAllInBatch();
            menuItemRepository.saveAll(menu);
            log.info("Save new menu");
        } else {
            log.warn("Saving menu failed");
            return false;
        }
        return true;
    }

    private ArrayList<MenuItem> getAllFromPDFMenu(){
        ArrayList<MenuItem> menu = new ArrayList<>(100);
        StringBuilder sb = new StringBuilder(5024);
        try {
            Document pdf = PDF.open(menuPath);
            pdf.pipe(new OutputTarget(sb));
            pdf.close();
        } catch (IOException exception) {
            log.warn("Something wrong with pdf "+ exception.getMessage());
        }
        try {
            String text = deleteUseless(sb.toString(), trashStrings);
            text = reformatGrill(text.substring(0, text.indexOf("ЗАКУСКИ"))) + text.substring(text.indexOf("ЗАКУСКИ"));
            menu.addAll(getMenuCategory(text, "ГРИЛЬ", "ЗАКУСКИ"));
            menu.addAll(getMenuCategory(text, "ЗАКУСКИ", "САЛАТЫ"));
            menu.addAll(getMenuCategory(text, "САЛАТЫ", "Мясо и птица"));
            menu.addAll(getMenuCategory(text, "Мясо и птица", "Рыба и морепродукты"));
            menu.addAll(getMenuCategory(text, "Рыба и морепродукты", "СУПЫ"));
            menu.addAll(getMenuCategory(text, "СУПЫ", "ПАСТА"));
            menu.addAll(getMenuCategory(text, "ПАСТА", "Чай"));
            menu.addAll(getMenuCategory(text, "ГАРНИРЫ", "ДЕСЕРТЫ"));
            menu.addAll(getMenuCategory(text, "ДЕСЕРТЫ", "Кофе"));
            menu.addAll(getMenuCategory(text, "Кофе", "end"));
        } catch (IndexOutOfBoundsException e) {
            log.warn("Something wrong with menu");
        }
        return menu;
    }


    private static ArrayList<String> trashStrings = new ArrayList<>(Arrays.asList(
            "за 100 гр",
            "Если у вас есть аллергия, просим сообщить нам об этом",
            "Данное меню является рекламным материалом. С контрольным меню можно ознакомиться у администрации.",
            "ззаа 110000 ггрр"));

    public static String deleteUseless(String text, ArrayList<String> trash) {
        for (String line : trash) {
            text = text.replaceAll(line, "");
        }
        return text;
    }

    public static ArrayList<MenuItem> getMenuCategory(String text, String category, String stopWord){
        String dishes = "none";
        ArrayList<MenuItem> dishesList = new ArrayList<>(20);
        dishes = text.substring(text.indexOf(category), stopWord.equals("end")? text.length() : text.indexOf(stopWord));
        for (String line : dishes.split("\n")) {
            if (!line.isEmpty() && !line.equals(category)) {
                line = line.trim().replaceAll("[ ]{3,}", ":");
                if (!line.isEmpty())
                    dishesList.add(new MenuItem(line, category));
            }
        }
        return dishesList;
    }
    public static String reformatGrill(String text) {
        text = text.replace("ГРИЛЬ", "");
        ArrayList<Integer> positions = new ArrayList<>();
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.')
                positions.add(i + 3);
        }
        int add = 0;
        for (Integer pos: positions) {
            if (sb.charAt(pos) != '\n') {
                sb.insert(pos + add, "\n");
                add++;
            }
        }
        return "ГРИЛЬ\n" + sb.toString();
    }
}
