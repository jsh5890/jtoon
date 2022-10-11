package jtoon.jmao5.duckdns.org.repository.jtoon;

import jtoon.jmao5.duckdns.org.domain.jtoon.JToon;

public interface JToonRepositoryCustom {

    Boolean getListExist(JToon jToon);

    JToon findByTitleId2(Long titleId, String dayOfWeek);
}
