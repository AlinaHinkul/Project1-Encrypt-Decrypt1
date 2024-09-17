package com.javarush.cryptoanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public Main() {

    }

    private static String encryption(String encryptTextBefore, int encryptKey) {
        char[] encryptCharBefore = encryptTextBefore.toCharArray();
        char[] encryptCharAfter = new char[encryptCharBefore.length];
        boolean isPresence = false;

        for (int i = 0; i < encryptCharBefore.length; ++i) {
            for (int k = 0; k < CharacterData.ENGLISH_ALPHABET_FULL_SIZE || k < CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE; ++k) {
                if (encryptCharBefore[i] == CharacterData.ALPHABET_ENGLISH_FULL[k]) {
                    isPresence = true;
                }

                if (isPresence) {
                    encryptCharAfter[i] = CharacterData.ALPHABET_ENGLISH_FULL[(k + encryptKey) % CharacterData.ENGLISH_ALPHABET_FULL_SIZE];
                    isPresence = false;
                }

                if (encryptCharBefore[i] == CharacterData.ALPHABET_UKRAINIAN_FULL[k]) {
                    isPresence = true;
                }

                if (isPresence) {
                    encryptCharAfter[i] = CharacterData.ALPHABET_UKRAINIAN_FULL[(k + encryptKey) % CharacterData.UKRAINIAN_ALPHABET_FULL_SIZE];
                    isPresence = false;
                }
            }
        }

        return new String(encryptCharAfter);
    }

    private static String decryption(String decryptTextBefore, int decryptKey) {
        char[] decryptCharBefore = decryptTextBefore.toCharArray();
        char[] decryptCharAfter = new char[decryptCharBefore.length];
        boolean isPresence = false;

        for (int i = 0; i < decryptCharBefore.length; ++i) {
            for (int k = 0; k < CharacterData.ENGLISH_ALPHABET_FULL_SIZE; ++k) {
                if (decryptCharBefore[i] == CharacterData.ALPHABET_ENGLISH_FULL[k]) {
                    isPresence = true;
                }

                if (isPresence) {
                    if (k - decryptKey > 0) {
                        decryptCharAfter[i] = CharacterData.ALPHABET_ENGLISH_FULL[(k - decryptKey) % CharacterData.ENGLISH_ALPHABET_FULL_SIZE];
                    } else {
                        decryptCharAfter[i] = CharacterData.ALPHABET_ENGLISH_FULL[(CharacterData.ENGLISH_ALPHABET_FULL_SIZE + (k - decryptKey)) % CharacterData.ENGLISH_ALPHABET_FULL_SIZE];
                    }

                    isPresence = false;
                }
            }
        }

        return new String(decryptCharAfter);
    }
// for review
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                System.out.println("Введіть шлях тексту для шифрування: ");
                byte[] buffer = Files.readAllBytes(Path.of(reader.readLine()));
                String encryptTextBefore = new String(buffer, StandardCharsets.UTF_8);
                System.out.println("Введіть ключ для шифрування у вигляді цифри: ");
                int encryptKey = Integer.parseInt(reader.readLine());
                System.out.println("Введіть шлях для створення файлу і запису зашифрованого тексту:");
                Path encryptFile = Files.createFile(Path.of(reader.readLine()));
                Files.writeString(encryptFile, encryption(encryptTextBefore, encryptKey));
                String decryptTextBefore = encryption(encryptTextBefore, encryptKey);
                System.out.println("Введіть шлях для створення файлу і запису розшифрованого тексту:");
                Path decryptFile = Files.createFile(Path.of(reader.readLine()));
                System.out.println("Введіть ключ для розшифрування у вигляді цифри: ");
                int decryptKey = Integer.parseInt(reader.readLine());
                Files.writeString(decryptFile, decryption(decryptTextBefore, decryptKey));
            } catch (Throwable var10) {
                try {
                    reader.close();
                } catch (Throwable var9) {
                    var10.addSuppressed(var9);
                }

                throw var10;
            }

            reader.close();
        } catch (IOException var11) {
            System.out.println("The file does not exist " + var11.getMessage());
        }

            System.out.printf("Hello and welcome!");

            for (int i = 1; i <= 5; i++) {
                //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
                // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
                System.out.println("i = " + i);
            }
    }

    public class Alphabet {
        public Alphabet() {
        }

        public static String detectAlphabet(String text) {
            if (CharacterData.ALPHABET_ENGLISH_FULL.equals(text)) {
                return "eng";
            } else {
                return CharacterData.ALPHABET_UKRAINIAN_FULL.equals(text) ? "ukr" : "unknown";
            }
        }

        public static void main(String[] args) {
            String text1 = "This is English text";
            String text2 = "Це український текст";
            String text3 = "Не відомо, який алфавіт";
            System.out.println(detectAlphabet(text1));
            System.out.println(detectAlphabet(text2));
            System.out.println(detectAlphabet(text3));
        }
    }
}
