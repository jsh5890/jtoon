package jtoon.jmao5.duckdns.org.domain.jposts;

import jtoon.jmao5.duckdns.org.common.util.CommonUtils;
import jtoon.jmao5.duckdns.org.domain.config.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_search", columnList = "titleId, writer, title"))
public class JPosts extends BaseTime {

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

    @Builder
    public JPosts(Provider provider, String writer, String title, String href, String src, String dayOfWeek,
                  Long titleId, String infoImg, String infoWrtNm) {
        this.provider = provider;
        this.writer = writer;
        this.title = title;
        this.href = href;
        this.src = src;
        this.dayOfWeek = dayOfWeek;
        this.titleId = titleId;
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
    }

    public static JPosts of(Element element, String day) {
        Elements aTag = element.select("dt a");
        String href = aTag.attr("abs:href");
        String title = aTag.text();
        Long titleId = Long.valueOf(CommonUtils.getQueryMap(aTag.attr("href")).get("titleId"));

        return JPosts.builder()
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
    public JPosts(Long id, String infoImg, String infoWrtNm) {
        this.id = id;
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
    }

    public void update(String infoImg, String infoWrtNm) {
        this.infoImg = infoImg;
        this.infoWrtNm = infoWrtNm;
    }

}
