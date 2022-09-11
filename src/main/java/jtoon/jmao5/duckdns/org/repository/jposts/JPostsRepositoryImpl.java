package jtoon.jmao5.duckdns.org.repository.jposts;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import lombok.RequiredArgsConstructor;

import static jtoon.jmao5.duckdns.org.domain.jposts.QJPosts.jPosts;

@RequiredArgsConstructor
public class JPostsRepositoryImpl implements JPostsRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean getListExist(JPosts jPostsExist) {
        Integer fetchOne = jpaQueryFactory
                        .selectOne()
                        .from(jPosts)
                        .where(jPosts.dayOfWeek.eq(jPostsExist.getDayOfWeek())
                        ,jPosts.provider.eq(jPostsExist.getProvider())
                        ,jPosts.title.eq(jPostsExist.getTitle())
                        ,jPosts.writer.eq(jPostsExist.getWriter())
                        ).fetchFirst();

        return fetchOne != null;
    }
}
