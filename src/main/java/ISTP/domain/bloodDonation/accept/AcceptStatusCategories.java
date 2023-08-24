package ISTP.domain.bloodDonation.accept;


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
@ToString(of = {"acceptStatus"})
public class AcceptStatusCategories {

    @Id
    @GeneratedValue
    @Column(name = "accept_status_categories_id")
    private Long id;
    @Column(name = "accept_status")
    private String acceptStatus;

    public AcceptStatusCategories(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }
}
