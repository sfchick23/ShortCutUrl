package by.sfchick.ShortCutUrl.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkDto {

    private Long id;

    private String url;

    private String shortUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int accessCount;
}
