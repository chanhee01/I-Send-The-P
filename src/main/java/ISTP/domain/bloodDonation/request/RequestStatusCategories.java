package ISTP.domain.bloodDonation.request;

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
@ToString(of = {"requestStatus"})
public class RequestStatusCategories {

    @Id
    @GeneratedValue
    @Column(name = "request_status_categories_id")
    private Long id;
    @Column(name = "request_status")
    private String requestStatus;

    public RequestStatusCategories(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
