package com.javarush.cryptoanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BruteForce {
    private static final int NUMBER_SMALL_SIZE = 0;
    private static final int NUMBER_MEDIUM_SIZE = 4;
    private static final int NUMBER_LARGE_SIZE = 8;
    private static final String SMALL_TEXT = "small";
    private static final String MEDIUM_TEXT = "medium";
    private static final String LARGE_TEXT = "large";

    public BruteForce() {
    }

    private static String decryptionEnglish(String encryptText, int decryptKey) {
        char[] decryptCharBefore = encryptText.toCharArray();
        char[] decryptCharAfter = new char[decryptCharBefore.length];
        boolean isPresence = false;

        for(int i = 0; i < decryptCharBefore.length; ++i) {
            for(int k = 0; k < com.javarush.cryptoanalyser.CharacterData.ENGLISH_ALPHABET_FULL_SIZE; ++k) {
                if (decryptCharBefore[i] == com.javarush.cryptoanalyser.CharacterData.ALPHABET_ENGLISH_FULL[k]) {
                    isPresence = true;
                }

                if (isPresence) {
                    if (k - decryptKey > 0) {
                        decryptCharAfter[i] = com.javarush.cryptoanalyser.CharacterData.ALPHABET_ENGLISH_FULL[(k - decryptKey) % com.javarush.cryptoanalyser.CharacterData.ENGLISH_ALPHABET_FULL_SIZE];
                    } else {
                        decryptCharAfter[i] = com.javarush.cryptoanalyser.CharacterData.ALPHABET_ENGLISH_FULL[(com.javarush.cryptoanalyser.CharacterData.ENGLISH_ALPHABET_FULL_SIZE + (k - decryptKey)) % com.javarush.cryptoanalyser.CharacterData.ENGLISH_ALPHABET_FULL_SIZE];
                    }

                    isPresence = false;
                }
            }
        }

        return new String(decryptCharAfter);
    }

    private static String decryptionUkrainian(String encryptText, int decryptKey) {
        char[] decryptCharBefore = encryptText.toCharArray();
        char[] decryptCharAfter = new char[decryptCharBefore.length];
        boolean isPresence = false;

        for(int i = 0; i < decryptCharBefore.length; ++i) {
            for(int k = 0; k < com.javarush.cryptoanalyser.CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE; ++k) {
                if (decryptCharBefore[i] == com.javarush.cryptoanalyser.CharacterData.ALPHABET_UKRAINIAN_FULL[k]) {
                    isPresence = true;
                }

                if (isPresence) {
                    if (k - decryptKey > 0) {
                        decryptCharAfter[i] = com.javarush.cryptoanalyser.CharacterData.ALPHABET_UKRAINIAN_FULL[(k - decryptKey) % com.javarush.cryptoanalyser.CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE];
                    } else {
                        decryptCharAfter[i] = com.javarush.cryptoanalyser.CharacterData.ALPHABET_UKRAINIAN_FULL[(com.javarush.cryptoanalyser.CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE + (k - decryptKey)) % com.javarush.cryptoanalyser.CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE];
                    }

                    isPresence = false;
                }
            }
        }

        return new String(decryptCharAfter);
    }

    private static int getKeyCharactersCount(String force) {
        int keyCharactersCount = 0;
        Iterator var2 = com.javarush.cryptoanalyser.CharacterData.KEY_CHARACTERS.iterator();

        while(var2.hasNext()) {
            String keyCh = (String)var2.next();
            if (force.contains(keyCh)) {
                ++keyCharactersCount;
            }
        }

        return keyCharactersCount;
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                System.out.println("Введите путь файла для взлома текста:");
                byte[] buffer = Files.readAllBytes(Path.of(reader.readLine()));
                String encryptText = new String(buffer, StandardCharsets.UTF_8);
                System.out.println("Введите размер зашифрованного текста\n(маленький текст - введите small\nсредний текст - введите medium\nбольшой текст - введите large):");
                String sizeText = reader.readLine();
                int sizeNumber = 0;
                switch (sizeText) {
                    case "small":
                        break;
                    case "medium":
                        sizeNumber = 4;
                        break;
                    case "large":
                        sizeNumber = 8;
                        break;
                    default:
                        System.out.println("Неправильно введён размер (по умолчанию выбран размер small)");
                }

                System.out.println("Введите путь для создания файла и записи взломанного текста:");
                Path forceTextFile = Files.createFile(Path.of(reader.readLine()));
                List<String> listVariants = new ArrayList();
                List<String> forceText = new ArrayList();

                int i;
                char variantCharacter;
                boolean endText;
                for(i = 0; i < com.javarush.cryptoanalyser.CharacterData.ENGLISH_ALPHABET_FULL_SIZE; ++i) {
                    listVariants.add(decryptionEnglish(encryptText, i));
                    variantCharacter = ((String)listVariants.get(i)).charAt(((String)listVariants.get(i)).length() - 1);
                    endText = variantCharacter == '!' || variantCharacter == '?' || variantCharacter == '.';
                    if (getKeyCharactersCount((String)listVariants.get(i)) > sizeNumber && endText) {
                        forceText.add((String)listVariants.get(i));
                    }
                }

                for(i = 0; i < com.javarush.cryptoanalyser.CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE; ++i) {
                    listVariants.add(decryptionUkrainian(encryptText, i));
                    variantCharacter = ((String)listVariants.get(i)).charAt(((String)listVariants.get(i)).length() - 1);
                    endText = variantCharacter == '!' || variantCharacter == '?' || variantCharacter == '.';
                    if (getKeyCharactersCount((String)listVariants.get(i)) > sizeNumber && endText) {
                        forceText.add((String)listVariants.get(i));
                    }
                }

                Files.write(forceTextFile, forceText);
            } catch (Throwable var13) {
                try {
                    reader.close();
                } catch (Throwable var12) {
                    var13.addSuppressed(var12);
                }

                throw var13;
            }

            reader.close();
        } catch (IOException var14) {
            System.out.println("The file does not exist " + var14.getMessage());
        }

    }
}

