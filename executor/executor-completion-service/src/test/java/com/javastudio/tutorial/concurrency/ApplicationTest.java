package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private static final Map<Character, Character> COMPLEMENT_MAP = Map.of(
            'A', 'T',
            'T', 'A',
            'C', 'G',
            'G', 'C'
    );
    public static String dnaComplement(String dna) {
        if (dna == null || dna.isEmpty()) return "";

        return dna.chars()
                .mapToObj(c -> (char) c)
                .map(COMPLEMENT_MAP::get)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Test
    void name() {
        System.out.println(dnaComplement("AB"));
    }
}