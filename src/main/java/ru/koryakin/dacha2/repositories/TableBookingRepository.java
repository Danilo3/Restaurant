package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.TableBooking;

public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {
}
