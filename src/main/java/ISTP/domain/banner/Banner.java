package ISTP.domain.banner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Banner {

    @Id @GeneratedValue
    private Long id;

    private String imgUrl;
    private String to_Url;

    public Banner(String url, String to_Url){
        this.imgUrl = url;
        this.to_Url = to_Url;
    }
}