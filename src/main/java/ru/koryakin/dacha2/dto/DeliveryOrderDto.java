package ru.koryakin.dacha2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.koryakin.dacha2.domain.TextView;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeliveryOrderDto implements TextView {

    private Integer id;
    private String comment;
    private String address;
    private String username;
    private String email;
    private String name;
    private String phone;
    private Double price;
    private String timeToDeliver;
    List<DeliveryOrderItemDto> orderItems;

    @Override
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Новый заказ\n")
                .append("Имя: ").append(this.name).append("\n")
                .append("Почта: ").append(this.email).append("\n")
                .append("Телефон: ").append(this.phone).append("\n")
                .append("Адрес: ").append(this.address).append("\n")
                .append("Время доставки: ").append(this.timeToDeliver).append("\n")
                .append("Общая сумма: ").append(this.price).append("\n")
                .append("Комментарий к заказу: ").append(this.comment).append("\n")
                .append("Блюда в заказе: \n").append(this.orderItems.toString()).append("\n");
        return sb.toString();
    }
}
