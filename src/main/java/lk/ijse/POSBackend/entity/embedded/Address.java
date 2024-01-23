package lk.ijse.POSBackend.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "City", nullable = false, length = 20)
    private String city;

    @Column(name = "Province", nullable = false)
    private String province;    
}
