package com.doodle.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameManager {

    private static final String FILE_PATH = "./src/main/java/com/doodle/game/words.txt";
    public static boolean running = false;

    public static String guess; // user attempt
    public static String word; // word to guess

    static int status;
    static int round;
    int currentTurn;

    List<String> guesses;
    HashMap<String, String> letterToEmoji;

    public GameManager() {
        guesses = new ArrayList<String>();

        status = 2;

        round = 0;
        currentTurn = 0;

        letterToEmoji();
    }

    public String run(int status) {
        // start game
        if (status == 0) {
            setWord();
            System.out.println(getWord());
            round++;
            this.status = 1;
            return startDisplay() + "Indovina con una parola di 5 lettere";
        } else if (this.status == 1) {
            if (currentTurn == 4) {
                boolean win = checkWin();
                if (win) {
                    running = false;
                    this.status = 2;
                    return display() + " COMPLIMENTI, SEI IL NUOVO WORDLE GLÖRYY! Hai vinto all'ultimo tentativo!";
                } else {
                    return display() + "ATTENTO QUESTO E' IL TUO ULTIMO TENTATIVO";
                }
            } else if (currentTurn < 5) {
                boolean win = checkWin();
                if (win) {
                    running = false;
                    this.status = 2;
                    return display() + "\nCOMPLIMENTI, SEI IL NUOVO WORDLE GLÖRYY! Hai vinto in " + currentTurn + "/5";
                }
                return display();
            } else {
                running = false;
                this.status = 2;
                return "Tentativi Finiti";
            }
        } else {
            return "Game non iniziato";
        }
    }

    private boolean checkWin() {
        if (word.equalsIgnoreCase(guess)) {
            return true;
        } else {
            return false;
        }
    }

    public String startDisplay() {
        String output = "WORDLE #" + String.valueOf(round) + "\n";
        return output;
    }

    public String display() {

        if (checkWordInList(randomWordList(), this.guess)) {
            currentTurn++;
            String output = "WORDLE  #" + round + "     " + currentTurn + "/5" + "\n\n" + guessToEmoji() + "\n" + getMatch(this.word, this.guess) + "\n";
            return output;
        } else {
            return "La parola non è in lista\n";
        }
    }

    private String getMatch(String word, String guess) {
        String match = "";
        String containsYellow = "";

        for (int i = 0; i < guess.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                match += "\uD83D\uDFE9" + " ";
            } else if (word.contains(String.valueOf(guess.charAt(i)))) {
                match += "\uD83D\uDFE8" + " ";

//                matchGreen += "⬜" + " ";
//                containsYellow += ""+guess.charAt(i);
            } else {
                match += "\uD83D\uDFE6" + " ";
            }
        }
        return match;
    }

    private String guessToEmoji() {
        String output = "";
        for (int i = 0; i < guess.length(); i++) {
            output += letterToEmoji.get("" + guess.charAt(i)) + " ";
        }
        return output;
    }

    public String randomWord() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                for (String word : wordsLine) {
                    words.add(word);
                }
                line = reader.readLine();
            }
            Random rand = new Random(System.currentTimeMillis());

            String randomWord = words.get(rand.nextInt(words.size()));
            return randomWord;

        } catch (Exception e) {
            System.out.println("ERROR: Exception in randomWord() method");
            return null;
        }
    }

    public List<String> randomWordList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                for (String word : wordsLine) {
                    words.add(word);
                }
                line = reader.readLine();
            }
            return words;

        } catch (Exception e) {
            System.out.println("ERROR: Exception in randomWordList() method");
            return null;
        }
    }

    public boolean checkWordInList(List<String> list, String guess) {
        if (list.contains(guess)) {
            return true;
        } else {
            return false;
        }
    }

    public void setWord() {
        word = randomWord();
    }

    public String getWord() {
        return word;
    }

    public void letterToEmoji() {
        letterToEmoji = new HashMap<String, String>();
        letterToEmoji.put("a", "\uD83C\uDDE6");
        letterToEmoji.put("b", "\uD83C\uDDE7");
        letterToEmoji.put("c", "\uD83C\uDDE8");
        letterToEmoji.put("d", "\uD83C\uDDE9");
        letterToEmoji.put("e", "\uD83C\uDDEA");
        letterToEmoji.put("f", "\uD83C\uDDEB");
        letterToEmoji.put("g", "\uD83C\uDDEC");
        letterToEmoji.put("h", "\uD83C\uDDED");
        letterToEmoji.put("i", "\uD83C\uDDEE");
        letterToEmoji.put("j", "\uD83C\uDDEF");
        letterToEmoji.put("k", "\uD83C\uDDF0");
        letterToEmoji.put("l", "\uD83C\uDDF1");
        letterToEmoji.put("m", "\uD83C\uDDF2");
        letterToEmoji.put("n", "\uD83C\uDDF3");
        letterToEmoji.put("o", "\uD83C\uDDF4");
        letterToEmoji.put("p", "\uD83C\uDDF5");
        letterToEmoji.put("q", "\uD83C\uDDF6");
        letterToEmoji.put("r", "\uD83C\uDDF7");
        letterToEmoji.put("s", "\uD83C\uDDF8");
        letterToEmoji.put("t", "\uD83C\uDDF9");
        letterToEmoji.put("u", "\uD83C\uDDFA");
        letterToEmoji.put("v", "\uD83C\uDDFB");
        letterToEmoji.put("w", "\uD83C\uDDFC");
        letterToEmoji.put("x", "\uD83C\uDDFD");
        letterToEmoji.put("y", "\uD83C\uDDFE");
        letterToEmoji.put("z", "\uD83C\uDDFF");
    }
}
