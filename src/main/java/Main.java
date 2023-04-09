import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    //final = const in JS
    //the ArrayList reads the information from the "input.txt" file.
    //a static List of SuperHero objects (superHeroList)
    private static final List<SuperHero> superHeroList = new ArrayList<>(); //empty array list

    public static void main(String[] args) {
        try { //exception handling
            readFile("src/main/java/input.txt");
        } catch (IOException e) { //catches an IO Exception if it is thrown
            System.out.println("Error while reading the file: " + e.getMessage());
        }
        startGame.playGame("Lucky Player");
    }

    /**
     * need to declare the nested static class first (startGame).
     * added a string parameter (playerName) that doesn't return a value.
     * need to declare a new arrayList of Questions.Question objects and assign it to questions.
     * when the getters are called from the superHeroList, a new question object is added to the questions list.
     */
    public static class startGame {
        public static void playGame(String playerName) {
            int wrongAnswers = 0;
            int correctAnswers = 0;

            List<Questions.Question> questions = new ArrayList<>();
            questions.add(new Questions.Question("\nWhat is Superman's real name?", superHeroList.get(0).getRealName()));
            questions.add(new Questions.Question("\nWhat is Batman's real name?", superHeroList.get(1).getRealName()));
            questions.add(new Questions.Question("\nWhat is Wonder Woman's real name?", superHeroList.get(2).getRealName()));

            questions.add(new Questions.Question("\nWhat Superhero character was created by writer Jerry Siegel?", superHeroList.get(0).getSuperHeroName()));
            questions.add(new Questions.Question("\nThe Joker is this superhero's arch-nemesis.", superHeroList.get(1).getSuperHeroName()));
            questions.add(new Questions.Question("\nDr. Psycho is this superhero's arch-nemesis.", superHeroList.get(2).getSuperHeroName()));

            questions.add(new Questions.Question("\nWhat planet was Superman born on?", superHeroList.get(0).getPlaceOfBirth()));
            questions.add(new Questions.Question("\nWhat city did Batman reside in?", superHeroList.get(1).getPlaceOfBirth()));
            questions.add(new Questions.Question("\nWonder Woman is from this sovereign city-state.", superHeroList.get(2).getPlaceOfBirth()));

            Scanner scanner = new Scanner(System.in); //needed to read the answers from the player

            /**
             *need a way to check if the player's answers are correct.
             *created a for loop to iterate through each Question object in the questions list.
             *need to extract the question and correct answer strings from the Question object,
             and print the question to the console.
             */
            for (Questions.Question question : questions) {
                String prompt = question.getQuestion();
                String correctAnswer = question.getAnswer();
                System.out.println(prompt);
                String playerAnswer = scanner.nextLine(); //reads the player's input answer

                /**
                 *need a condition to check if the player's answer matches the correct answer.
                 *to make things simple, case sensitivity is ignored.
                 *both variables correctAnswers & wrongAnswers are incremented to keep track of the score, which
                 was initialized with 0 in the beginning.
                 */

                if (playerAnswer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("\nThat's Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("\nIncorrect Answer! The correct answer is " + correctAnswer + ".");
                    wrongAnswers++;
                }
            }


            /**
             *need to print to the console the winning or losing score and messages.
             */
            System.out.println("\nYou got " + correctAnswers + " out of " + questions.size() + " questions correct!");

            if (correctAnswers > wrongAnswers) {
                System.out.println(playerName + ", you won the game!");
            } else {
                System.out.println(playerName + ", you lost the game!");
            }


        /**need to write the results to the output.txt file; tested game before adding FileWriter**
         *using try/catch to write the results of the game to the output.txt file.
         *found the FileWriter class in the Java documentation for FileWriter (writing character files).
         *the writer object is created to write the player's name and results (total# of questions, # of correct/wrong answers)
              to the output.txt file.
         *the writer object has to be closed to release any system resources that may have been
              used to write to the file.
         *the catch block will be executed if any type of IOException is thrown by the try block.*/
   try {

            FileWriter writer = new FileWriter("output.txt");
            writer.write("Game results for " + playerName + ": ");
            writer.write("\nTotal questions: " + questions.size());
            writer.write("\nCorrect answers: " + correctAnswers);
            writer.write("\nWrong answers: " + wrongAnswers);
            writer.write("\n\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing the game results to the output file.");
            e.printStackTrace();
        }
    }
}



    /***
     * A new superhero object is created, and it is added to the superHeroes ArrayList by reading the
     * @param fileName
     * @throws IOException
     */
    public static void readFile(String fileName) throws IOException {
        // Converts a path string, or a sequence of strings that
        // when joined form a path string, to a Path
        Path pathToFile = Paths.get(fileName);
        // BufferedReader helps us to read the file
        BufferedReader reader;
        try {
            // Read the file content with the given Uri (meaning the local path to the file)
            reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()));
            // we read one line at a time from the BufferReader
            String currentLine = reader.readLine();

            while (currentLine != null) { // check if EOF
                SuperHero superHero = new SuperHero(); // create new SuperHero object for each row
                // split the line by ,
                // Superman,Clark Kent,Krypton
                String[] data = currentLine.split(",");

                // we set data to SuperHero object
                superHero.setSuperHeroName(data[0]);
                superHero.setRealName(data[1]);
                superHero.setPlaceOfBirth(data[2]);

                // we add the object to superHeroList list
                superHeroList.add(superHero);
                currentLine = reader.readLine(); // read next line
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}



