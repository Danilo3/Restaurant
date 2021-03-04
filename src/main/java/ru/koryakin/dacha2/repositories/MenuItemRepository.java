package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.koryakin.dacha2.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findAllByCategory(String category);
}
