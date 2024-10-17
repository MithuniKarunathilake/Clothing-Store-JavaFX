package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Customer {
    private String id;
    private String title;
    private String name;
    private LocalDate DOB;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    public Customer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode) {
        this.id = id;
        this.title = title;
        this.name = title+name;
        this.DOB = DOB;
        this.salary = salary;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }
}
