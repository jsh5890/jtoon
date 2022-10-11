package jtoon.jmao5.duckdns.org.response.jtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JToonInfoResponse {

    private Long id;
    private String infoImg;
    private String infoWrtNm;

    @Builder
    public JToonInfoResponse(Long id, String infoImg, String infoWrtNm) {
        this.id = id;
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
    }

}
