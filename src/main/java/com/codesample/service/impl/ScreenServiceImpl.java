package com.codesample.service.impl;

import com.codesample.dto.screen.SentenceDTO;
import com.codesample.service.ScreenService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.codesample.constants.Constants.Screen.SENTENCE_SPLIT_REGEX;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Override
    public SentenceDTO reverseSentence(SentenceDTO sentenceDTO) {

        String[] words = sentenceDTO.getSentence().split(SENTENCE_SPLIT_REGEX);

        String reversedSentence = Arrays
                .stream(words)
                .reduce("", (partialSentence, word) -> word + " " + partialSentence);

        sentenceDTO.setSentence(reversedSentence.trim());

        return sentenceDTO;
    }
}
