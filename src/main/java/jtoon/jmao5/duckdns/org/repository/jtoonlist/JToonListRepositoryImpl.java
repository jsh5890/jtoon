package jtoon.jmao5.duckdns.org.repository.jtoonlist;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jtoon.jmao5.duckdns.org.domain.jtoonlist.JToonList;
import jtoon.jmao5.duckdns.org.domain.jtoonlist.QJToonList;
import lombok.RequiredArgsConstructor;

import static jtoon.jmao5.duckdns.org.domain.jtoonlist.QJToonList.jToonList;

@RequiredArgsConstructor
public class JToonListRepositoryImpl implements JToonListRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean getListExist(JToonList JToonListExist) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(jToonList)
                .where(jToonList.date.eq(JToonListExist.getDate())
                        , jToonList.title.eq(JToonListExist.getTitle())
                )
                .fetchFirst();

        return fetchOne != null;
    }
}
