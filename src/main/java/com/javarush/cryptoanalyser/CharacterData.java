package com.javarush.cryptoanalyser;

import java.util.Arrays;
import java.util.List;

public class CharacterData {
    public static final char[] ALPHABET_ENGLISH_FULL = new char[]{'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    public static final int ENGLISH_ALPHABET_FULL_SIZE;
    public static final char[] ALPHABET_UKRAINIAN_FULL;
    public static final int UKRAINIAN_ALPHABET_FULL_SIZE;
    public static final List<String> KEY_CHARACTERS;

    public CharacterData() {
    }
    //for src
    static {
        ENGLISH_ALPHABET_FULL_SIZE = ALPHABET_ENGLISH_FULL.length;
        ALPHABET_UKRAINIAN_FULL = new char[]{'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Ґ', 'ґ', 'Д', 'д', 'Е', 'е', 'Є', 'є', 'Ж', 'ж', 'З', 'з', 'И', 'и', 'І', 'і', 'Ї', 'ї', 'Й', 'й', 'К', 'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о', 'П', 'п', 'Р', 'р', 'С', 'с', 'Т', 'т', 'У', 'у', 'Ф', 'ф', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ', 'Ь', 'ь', 'Ю', 'ю', 'Я', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
        UKRAINIAN_ALPHABET_FULL_SIZE = ALPHABET_ENGLISH_FULL.length;
        KEY_CHARACTERS = Arrays.asList(" без ", " в ", " до ", " для ", " за ", " из ", " за ", " к ", " на ", " над ", " об ", " от ", " перед ", " под ", " при ", " с ", " у ", " через ", ". ", ", ", "? ", ": ", "; ", "! ", "-", ") ", " (", " ");
    }
}
