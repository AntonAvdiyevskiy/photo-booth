package com.codesample.controller.screen;

import com.codesample.dto.screen.SentenceDTO;
import com.codesample.service.ScreenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-booth")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PutMapping(value = "/sentence/reverse", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Reverse input sentence"
    )
    public SentenceDTO reverseString(@Valid @RequestBody SentenceDTO sentenceDTO) {
        return screenService.reverseSentence(sentenceDTO);
    }


}
