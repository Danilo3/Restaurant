package ru.koryakin.dacha2.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.koryakin.dacha2.domain.TableBooking;

public interface TableBookingRepository extends CrudRepository<TableBooking, Integer> {
}
