package peaksoft.models;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contact {
    private Long id;
    private String contactName;
    private int phoneNumber;
    private int phoneId;

    public Contact(String contactName, int phoneNumber, int phoneId) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.phoneId = phoneId;
    }
}
