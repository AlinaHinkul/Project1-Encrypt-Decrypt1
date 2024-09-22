package com.javarush.cryptoanalyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FrequencyAnalysis {
    public FrequencyAnalysis() {
    }
    //for src
    public static void main(String[] args) {
        String command = args[0];
        String filePath = args[1];
        String keyFilePath = args[2];
        String text = readFile(filePath);
        Map<Character, Integer> frequencyMap = calculateFrequencyMap(text);
        if (command.equals("BRUTE_FORCE")) {
            String key = findKey(frequencyMap);
            writeKeyToFile(keyFilePath, key);
            System.out.println("Key found: " + key);
        } else if (command.equals("STATIC_ANALYSIS")) {
            System.out.println("Frequency map:");
            printFrequencyMap(frequencyMap);
        } else {
            System.out.println("Invalid command: " + command);
        }

    }

    public static String readFile(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return null;
    }

    public static Map<Character, Integer> calculateFrequencyMap(String text) {
        Map<Character, Integer> frequencyMap = new HashMap();

        for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (frequencyMap.containsKey(c)) {
                int frequency = (Integer)frequencyMap.get(c);
                frequencyMap.put(c, frequency + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }

        return frequencyMap;
    }

    public static String findKey(Map<Character, Integer> frequencyMap) {
        return "TEST_KEY";
    }

    public static void writeKeyToFile(String keyFilePath, String key) {
    }

    public static void printFrequencyMap(Map<Character, Integer> frequencyMap) {
        Set<Character> characters = frequencyMap.keySet();
        Iterator var2 = characters.iterator();

        while(var2.hasNext()) {
            Character c = (Character)var2.next();
            int frequency = (Integer)frequencyMap.get(c);
            System.out.println("" + c + ": " + frequency);
        }

    }
}