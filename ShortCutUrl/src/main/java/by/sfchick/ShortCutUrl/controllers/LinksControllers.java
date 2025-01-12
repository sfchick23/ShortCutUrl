package by.sfchick.ShortCutUrl.controllers;

import by.sfchick.ShortCutUrl.DTO.LinkDto;
import by.sfchick.ShortCutUrl.DTO.ShortLinkDto;
import by.sfchick.ShortCutUrl.models.ShortLink;
import by.sfchick.ShortCutUrl.services.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/links")
public class LinksControllers {

    private final ShortLinkService shortLinkService;

    @Autowired
    public LinksControllers(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping("/shorten/{shortUrl}")
    public ShortLink shorten(@PathVariable String shortUrl)  {
        return shortLinkService.incrementAccessCount(shortUrl);
    }

    @GetMapping("/shorten/{shortUrl}/stats")
    public ShortLinkDto stats(@PathVariable String shortUrl)  {
        return shortLinkService.statsLink(shortUrl);
    }


    @PostMapping("/shorten")
    public ShortLink shorten(@RequestBody LinkDto shortLinkDto) {
        return shortLinkService.createdShortLink(shortLinkDto.getUrl());
    }

    @PutMapping("/shorten/{shortUrl}")
    public ShortLink shorten(@PathVariable String shortUrl, @RequestBody LinkDto shortLinkDto) {
        return shortLinkService.updateOriginalLink(shortUrl ,shortLinkDto.getUrl());
    }

    @DeleteMapping("/shorten/{shortUrl}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String shortUrl) {
        if (shortLinkService.findByShortLink(shortUrl).isPresent()) {
            shortLinkService.delete(shortUrl);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
