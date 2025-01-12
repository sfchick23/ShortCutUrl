package by.sfchick.ShortCutUrl.repositories;

import by.sfchick.ShortCutUrl.models.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByShortUrl(String shortUrl);
}
