package ISTP.domain.bloodDonation;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"bloodType"})
public class BloodTypeCategories {


    @Id
    @GeneratedValue
    @Column(name = "blood_type_categories_id")
    private Long id;
    @Column(name = "blood_type")
    private String bloodType;

    public BloodTypeCategories(String bloodType) {
        this.bloodType = bloodType;
    }
}
