package com.codesample.service.impl;

import com.codesample.dto.screen.SentenceDTO;
import com.codesample.service.ScreenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScreenServiceImplTest {

    private ScreenService screenService;

    @BeforeEach
    public void setUp() {
        screenService = new ScreenServiceImpl();
    }

    @Test
    public void testReverseSentence_singleSpaceSeparator() {
        String sentence = "Hello, world! This is a sample sentence.";
        String expected = "sentence. sample a is This world! Hello,";
        SentenceDTO sentenceDTO = new SentenceDTO();
        sentenceDTO.setSentence(sentence);
        SentenceDTO reversedSentence = screenService.reverseSentence(sentenceDTO);

        Assertions.assertEquals(expected, reversedSentence.getSentence());
    }

    @Test
    public void testReverseSentence_multipleSpacesSeparator() {
        String sentence = "Hello,     world!   This    is   a   sample sentence.";
        String expected = "sentence. sample a is This world! Hello,";
        SentenceDTO sentenceDTO = new SentenceDTO();
        sentenceDTO.setSentence(sentence);

        SentenceDTO reversedSentence = screenService.reverseSentence(sentenceDTO);
        Assertions.assertEquals(expected, reversedSentence.getSentence());
    }

    @Test
    public void testReverseSentence_emptySentence() {
        String sentence = "";
        String expected = "";
        SentenceDTO sentenceDTO = new SentenceDTO();
        sentenceDTO.setSentence(sentence);

        SentenceDTO reversedSentence = screenService.reverseSentence(sentenceDTO);
        Assertions.assertEquals(expected, reversedSentence.getSentence());
    }

    @Test
    public void testReverseSentence_singleWord() {
        String sentence = "Hello";
        String expected = "Hello";
        SentenceDTO sentenceDTO = new SentenceDTO();
        sentenceDTO.setSentence(sentence);

        SentenceDTO reversedSentence = screenService.reverseSentence(sentenceDTO);
        Assertions.assertEquals(expected, reversedSentence.getSentence());
    }

}