package ValidSentenceProblem;

import main.AbstractProblem;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>Create a basic sentence checker that takes in a stream of characters and determines whether they form valid sentences. If a sentence is valid, the program should print it out.</p>
 *
 * <p>We can consider a sentence valid if it conforms to the following rules:</p>
 *
 *  <p><ul>
 *      <li>The sentence must start with a capital letter, followed by a lowercase letter or a space.</li>
 *      <li>All other characters must be lowercase letters, separators (, ; :) or terminal marks (. ? ! ‽).</li>
 *      <li>There must be a single space between each word.</li>
 *      <li>The sentence must end with a terminal mark immediately following a word.</li>
 * </ul></p>
 */

public class ValidSentenceProblem extends AbstractProblem {
    public static void main(String[] args) {
        ValidSentenceProblem p = new ValidSentenceProblem();
        printSol = true;
        System.out.println(p.solve());
    }

    char[][] s = {"asd".toCharArray(),
                "This is a sentence.".toCharArray(),
                "This is sort, of, a sentence.".toCharArray(),
                "This is N?O!T  A sentence".toCharArray()};

    @Override
    protected String naiveSolution() {
        List<Boolean> o = new LinkedList<>();
        for (char[] c : s) {
            o.add(isSentence(c));
        }
        return o.toString();
    }

    @Override
    protected String GivenSolution() {
        List<Boolean> o = new LinkedList<>();
        for (char[] c : s) {
            o.add(GFG.checkSentence(c));
        }
        return o.toString();
    }

    private boolean isSentence(char[] sentence) {
        // return false if not large enough to sate requirements.
        if (sentence.length < 2) return false;

        // The sentence must start with a capital letter, followed by a lowercase letter or a space.
        if (!Character.isUpperCase(sentence[0]))  return false;

        // The sentence must end with a terminal mark immediately following a word.
        if (!Character.isAlphabetic(sentence[sentence.length - 2])) return false;
        switch (sentence[sentence.length - 1]) {
            case '.':
            case '?':
            case '!':
            case '‽':
                break;
            default:
                return false;
        }

        // All other characters must be lowercase letters, separators (, ; :) or terminal marks (. ? ! ‽).
        // There must be a single space between each word.
        boolean wasSpace = false;
        for (int i = 1; i < sentence.length; i++) {
            char c = sentence[i];
            if (Character.isUpperCase(c)) return false;
            if (!Character.isAlphabetic(c)) {
                switch (c) {
                    // separators
                    case ' ':
                        if (wasSpace) return false;
                        wasSpace = true;
                        break;
                    case ',':
                    case ';':
                    case ':':
                    // terminal marks
                    case '.':
                    case '?':
                    case '!':
                    case '‽':
                        wasSpace = false;
                        break;
                    default:
                        return false;
                }
            } else {
                wasSpace = false;
            }
        }

        return true;
    }
}

// Java program to validate a given sentence
// for a set of rules
class GFG
{

    // Method to check a given sentence for given rules
    static boolean checkSentence(char[] str)
    {

        // Calculate the length of the string.
        int len = str.length;

        // Check that the first character lies in [A-Z].
        // Otherwise return false.
        if (str[0] < 'A' || str[0] > 'Z')
            return false;

        // If the last character is not a full stop(.)
        // no need to check further.
        if (str[len - 1] != '.')
            return false;

        // Maintain 2 states. Previous and current state
        // based on which vertex state you are.
        // Initialise both with 0 = start state.
        int prev_state = 0, curr_state = 0;

        // Keep the index to the next character in the string.
        int index = 1;

        // Loop to go over the string.
        while (index <= str.length)
        {

            // Set states according to the input characters
            // in the string and the rule defined in the description.
            // If current character is [A-Z]. Set current state as 0.
            if (str[index] >= 'A' && str[index] <= 'Z')
                curr_state = 0;

                // If current character is a space.
                // Set current state as 1.
            else if (str[index] == ' ')
                curr_state = 1;

                // If current character is [a-z].
                // Set current state as 2.
            else if (str[index] >= 'a' && str[index] <= 'z')
                curr_state = 2;

                // If current state is a dot(.).
                // Set current state as 3.
            else if (str[index] == '.')
                curr_state = 3;

            // Validates all current state with previous state
            // for the rules in the description of the problem.
            if (prev_state == curr_state && curr_state != 2)
                return false;

            if (prev_state == 2 && curr_state == 0)
                return false;

            // If we have reached last state and previous state
            // is not 1, then check next character. If next character
            // is '\0', then return true, else false
            if (curr_state == 3 && prev_state != 1)
                return (index + 1 == str.length);

            index++;

            // Set previous state as current state
            // before going over to the next character.
            prev_state = curr_state;
        }
        return false;
    }

    // Driver Code
    public static void main(String[] args)
    {
        String[] str = { "I love cinema.", "The vertex is S.",
                "I am single.", "My name is KG.",
                "I lovE cinema.", "GeeksQuiz. is a quiz site.",
                "I love Geeksquiz and Geeksforgeeks.",
                " You are my friend.", "I love cinema" };
        int str_size = str.length;

        int i = 0;
        for (i = 0; i < str_size; i++)
        {
            if (checkSentence(str[i].toCharArray()))
                System.out.println("\"" + str[i] +
                        "\"" + " is correct");
            else
                System.out.println("\"" + str[i] +
                        "\"" + " is incorrect");
        }
    }
}

// This code is contributed by
// sanjeev2552
