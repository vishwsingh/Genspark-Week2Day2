import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class TestWordStream {

    public static boolean isPalindrome(String s)
    {
        String new_S  = s.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, new_S.length() / 2).noneMatch(i -> new_S.charAt(i) != new_S.charAt(new_S.length() - i - 1));
    }
    public static void main(String[] args) throws IOException {

      //  String file_path = "/C://Users//vishw//Downloads//words.txt";
//        InputStream st = new FileInputStream(file_path);
//
//        Scanner in = new Scanner(st);
//        int no_of_Lines = 0;
//        while(in.hasNextLine())
//        {
//            no_of_Lines++;
//            in.nextLine();
//
//        }
//
//        System.out.println(no_of_Lines);

        Path path = Paths.get("words.txt");

        //a) to count the number of lines
        try {
            long no_of_Lines = Files.lines(path).count();

            System.out.println("count " + no_of_Lines);
        } catch (Exception e) {
            System.out.println("Hi" + e);
        }

        System.out.println("");
        System.out.println("First 100 words");
        System.out.println("");
        //b) printing first 100 words from the file
        try {
            Files.lines(path)
                    .limit(100)
                    .forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Hi" + e);
        }
        System.out.println("");
        System.out.println("Atleat size 22 words");
        System.out.println("");
        //c) printing the words that are at least of length 22
        try {
            Files.lines(path)
                    .filter(i -> i.length() >= 22)
                    .forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Hi" + e);
        }


        System.out.println("");
        System.out.println("Palindrome");
        System.out.println("");

        //e) Prinitng all the palindromes
        try {
            Files.lines(path)
                    .filter(TestWordStream::isPalindrome)
                    .forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Hi" + e);
        }

        //g) find min, max and average length of words in the file
        try {
            long min_length = Files.lines(path)
                    .mapToLong(String::length).summaryStatistics().getMin();

            long max_length = Files.lines(path)
                    .mapToLong(String::length).summaryStatistics().getMax();

            double avg_length = Files.lines(path)
                    .mapToLong(String::length).summaryStatistics().getAverage();
                  //  .forEach(System.out::println);
            System.out.println("Min " + min_length);
            System.out.println("Max " + max_length);
            System.out.println("Avg " + avg_length);
        } catch (Exception e) {
            System.out.println("Hi" + e);
        }

        //h) Grouping the words by length
        try {
            Map<Integer, List<String>> groupByLength = Files.lines(path)
                    .collect(Collectors.groupingBy(String::length));

           // System.out.println(groupByLength);
        } catch (Exception e) {
            System.out.println("Hi" + e);
        }

        //i)
        try {
            Map<Character, Long> no_character= Files.lines(path)
                    .flatMap(i -> i.chars().mapToObj(c -> (char) c))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

             System.out.println(no_character);
        } catch (Exception e) {
            System.out.println("Hi" + e);
        }

    }
}
