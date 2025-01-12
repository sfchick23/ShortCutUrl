package by.sfchick.ShortCutUrl.services;

import by.sfchick.ShortCutUrl.DTO.ShortLinkDto;
import by.sfchick.ShortCutUrl.models.ShortLink;
import by.sfchick.ShortCutUrl.repositories.ShortLinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShortLinkService(ShortLinkRepository shortLinkRepository, ModelMapper modelMapper) {
        this.shortLinkRepository = shortLinkRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<ShortLink> findByShortLink(String shortLink) {
        return shortLinkRepository.findByShortUrl(shortLink);
    }

    public ShortLinkDto statsLink(String shortLink){
        ShortLink link = findByShortLink(shortLink).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ShortLinkDto linkDto = new ShortLinkDto();
        return modelMapper.map(link, ShortLinkDto.class);
    }

    @Transactional
    public ShortLink createdShortLink(String originalUrl) {
        String shortUrl = generationShortUrl(originalUrl);
        LocalDateTime timeNow = LocalDateTime.now();
        ShortLink shortLink = new ShortLink();
        shortLink.setUrl(originalUrl);
        shortLink.setShortUrl(shortUrl);
        shortLink.setCreatedAt(timeNow);
        shortLink.setUpdatedAt(timeNow);
        shortLink.setAccessCount(1);
        return shortLinkRepository.save(shortLink);
    }

    @Transactional
    public ShortLink updateOriginalLink(String shortLink, String originalUrl) {
        ShortLink link = findByShortLink(shortLink).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));;
        link.setUrl(originalUrl);
        link.setUpdatedAt(LocalDateTime.now());
        return shortLinkRepository.save(link);
    }

    @Transactional
    public void delete(String shortLink) {
        ShortLink linkToDelete = findByShortLink(shortLink).orElse(null);
        if (linkToDelete != null) {
            shortLinkRepository.delete(linkToDelete);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short link not found");
        }
    }

    private String generationShortUrl(String originalUrl) {
        String base64Url = Base64.getEncoder().encodeToString(originalUrl.getBytes());

        return getRandomCharacters(base64Url, 6);
    }

    private String getRandomCharacters(String str, int length) {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(str.length());
            randomString.append(str.charAt(index));
        }

        return randomString.toString();
    }

    @Transactional
    public ShortLink incrementAccessCount(String shortLink) {
        ShortLink link = findByShortLink(shortLink)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Short link not found"));
        link.setAccessCount(link.getAccessCount() + 1);
        return shortLinkRepository.save(link);
    }
}
