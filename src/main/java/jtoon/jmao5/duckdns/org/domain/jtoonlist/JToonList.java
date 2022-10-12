package jtoon.jmao5.duckdns.org.domain.jtoonlist;

import jtoon.jmao5.duckdns.org.domain.jtoon.JToon;
import jtoon.jmao5.duckdns.org.domain.jtoon.Provider;
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class JToonList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String href;

    private String title;

    private String src;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_jtoonlist_to_jtoon"))
    private JToon jToon;

    @Builder
    public JToonList(String href, String title, String src, String date) {
        this.href = href;
        this.title = title;
        this.src = src;
        this.date = date;
    }

    public static JToonList of(Element r) {

        Elements aElement = r.select("td.title");
        String date = r.select("td.num").text();

        return JToonList.builder()
                .href(aElement.select("a").attr("abs:href"))
                .title(aElement.select("a").text())
                .src(r.select("img").attr("abs:src"))
                .date(date)
                .build();
    }
}
