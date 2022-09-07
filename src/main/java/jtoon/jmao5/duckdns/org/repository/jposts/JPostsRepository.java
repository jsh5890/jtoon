package jtoon.jmao5.duckdns.org.repository.jposts;

import jtoon.jmao5.duckdns.org.domain.jposts.JPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPostsRepository extends JpaRepository<JPosts, Long> {
}
