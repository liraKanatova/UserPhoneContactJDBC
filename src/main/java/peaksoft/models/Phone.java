package peaksoft.models;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Phone {
    private Long id;
    private String model;
    private String brand;
    private int price;
    private int userId;

    public Phone(String model, String brand, int price, int userId) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.userId = userId;
    }
}

