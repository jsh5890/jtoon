package jtoon.jmao5.duckdns.org.domain.jtoon;

import jtoon.jmao5.duckdns.org.common.util.CommonUtils;
import jtoon.jmao5.duckdns.org.domain.config.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_search", columnList = "titleId, writer, title"))
public class JToon extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String writer;

    private String title;

    private String href;

    private String src;

    private String dayOfWeek;

    private Long titleId;

    private String infoImg;

    private String infoWrtNm;

    private int totalCnt;

    @Builder
    public JToon(Provider provider, String writer, String title, String href, String src,
                 String dayOfWeek, Long titleId, String infoImg, String infoWrtNm, int totalCnt) {
        this.provider = provider;
        this.writer = writer;
        this.title = title;
        this.href = href;
        this.src = src;
        this.dayOfWeek = dayOfWeek;
        this.titleId = titleId;
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
        this.totalCnt = totalCnt;
    }

    public static JToon of(Element element, String day) {
        Elements aTag = element.select("dt a");
        String href = aTag.attr("abs:href");
        String title = aTag.text();
        Long titleId = Long.valueOf(CommonUtils.getQueryMap(aTag.attr("href")).get("titleId"));

        return JToon.builder()
                .provider(Provider.valueOf("naver"))
                .title(title)
                .titleId(titleId)
                .dayOfWeek(day)
                .href(href)
                .src(element.select("img").attr("abs:src"))
                .writer(element.select(".desc a").text())
                .build();
    }

    @Builder
    public JToon(Long id, String infoImg, String infoWrtNm) {
        this.id = id;
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
    }

    public void update(String infoImg, String infoWrtNm, int totalCnt) {
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
        this.totalCnt = totalCnt;
    }

}
