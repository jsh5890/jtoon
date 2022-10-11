package jtoon.jmao5.duckdns.org.repository.jtoon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jtoon.jmao5.duckdns.org.domain.jtoon.JToon;
import lombok.RequiredArgsConstructor;

import static jtoon.jmao5.duckdns.org.domain.jtoon.QJToon.jToon;

@RequiredArgsConstructor
public class JToonRepositoryImpl implements JToonRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean getListExist(JToon jToonExist) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(jToon)
                .where(jToon.dayOfWeek.eq(jToonExist.getDayOfWeek())
                        , jToon.provider.eq(jToonExist.getProvider())
                        , jToon.title.eq(jToonExist.getTitle())
                        , jToon.writer.eq(jToonExist.getWriter())
                ).fetchFirst();

        return fetchOne != null;
    }

    @Override
    public JToon findByTitleId2(Long titleId, String dayOfWeek) {
//        return jpaQueryFactory.select(Projections.constructor(JPosts.class,
//                                     jPosts.id
//                                    , jPosts.infoImg
//                                    , jPosts.infoWrtNm
//                                    ))
//                                    .from(jPosts)
//                                    .where(jPosts.titleId.eq(titleId), jPosts.dayOfWeek.eq(dayOfWeek))
//                                    .fetchOne();
        return jpaQueryFactory.selectFrom(jToon)
                                    .where(jToon.titleId.eq(titleId), jToon.dayOfWeek.eq(dayOfWeek))
                                    .fetchOne();

    }
}
