package jtoon.jmao5.duckdns.org.repository.jposts;

import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;

public interface JPostsRepositoryCustom {

    Boolean getListExist(JPosts jPosts);

    JPosts findByTitleId2(Long titleId);
}
