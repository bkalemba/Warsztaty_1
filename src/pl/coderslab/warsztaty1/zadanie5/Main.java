package pl.coderslab.warsztaty1.zadanie5;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String url = "http://www.stackoverflow.com/";
        String spanClass = "a.question-hyperlink";
        Path path = Paths.get("popular-words.txt");
        try {
            ArrayList<String> content = downloadContent(url, spanClass);
            ArrayList<String> words = replaceAndSplit(content);
            Files.write(path, words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> downloadContent(String url, String spanClass) throws IOException {
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements links = document.select(spanClass);
        ArrayList<String> pageContent = new ArrayList<>();
        for (Element elem : links) {
            pageContent.add(elem.ownText());
            System.out.println(elem.ownText());
        }
        return pageContent;
    }

    private static ArrayList<String> replaceAndSplit(ArrayList<String> content) {
        String[] charsToReplace = {"?", ".", "!", "(", ")", "-", ",", ":", "'"};
        String[] replacement = {"","","","","","","","",""};
        ArrayList<String> result = new ArrayList<>();
        for (String line : content) {
            String clearedLine = StringUtils.replaceEach(line, charsToReplace, replacement);
            String[] words = clearedLine.split(" ");
            for (String word : words) {
                if (word.length() >= 3) {
                    result.add(word);
                }

            }
        }
        return result;
    }
}
