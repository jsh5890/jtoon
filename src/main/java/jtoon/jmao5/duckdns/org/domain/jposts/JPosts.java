package jtoon.jmao5.duckdns.org.domain.jposts;

import jtoon.jmao5.duckdns.org.domain.config.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public JPosts(Provider provider, String writer, String title, String href, String src, String dayOfWeek) {
        this.provider = provider;
        this.writer = writer;
        this.title = title;
        this.href = href;
        this.src = src;
        this.dayOfWeek = dayOfWeek;
    }
    //    public static JPosts of(Element element) {
//        log.info("element : " + element);
//        return JPosts.builder()
//                .provider(Provider.valueOf("naver"))
//                .title(element.select("dt a").text())
//                .href(element.select("dt a").attr("abs:href"))
//                .src(element.select("img").attr("abs:src"))
//                .writer(element.select(".desc a").text())
//                .build();
//    }
}
