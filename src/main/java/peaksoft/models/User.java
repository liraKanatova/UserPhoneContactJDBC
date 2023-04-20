package peaksoft.models;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

public class User {
    private Long id;
    private String fullName;
    private int age;


    public User(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }
}
