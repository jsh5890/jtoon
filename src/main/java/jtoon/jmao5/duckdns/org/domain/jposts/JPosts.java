package jtoon.jmao5.duckdns.org.domain.jposts;

import jtoon.jmao5.duckdns.org.domain.config.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JPosts extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String href;

    private String src;

    private String writer;

    @Builder
    public JPosts(String title, String href, String src, String writer) {
        this.title = title;
        this.href = href;
        this.src = src;
        this.writer = writer;
    }

    public static JPosts of(Element element) {

        return JPosts.builder()
                .title(element.select("dt a").text())
                .href(element.select("dt a").attr("abs:href"))
                .src(element.select("img").attr("abs:src"))
                .writer(element.select(".desc a").text())
                .build();
    }
}
