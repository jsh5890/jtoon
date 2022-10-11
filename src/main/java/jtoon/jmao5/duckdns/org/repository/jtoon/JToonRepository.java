package jtoon.jmao5.duckdns.org.repository.jtoon;

import jtoon.jmao5.duckdns.org.domain.jtoon.JToon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JToonRepository extends JpaRepository<JToon, Long>, JToonRepositoryCustom {
    JToon findByTitleId(Long titleId);
}
