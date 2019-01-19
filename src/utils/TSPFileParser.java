package utils;

import models.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TSPFileParser {
    private static String pattern = "[0-9]{1,4}\\s[0-9]{1,4}\\s[0-9]{1,4}";

    public static List<Point> readData(String file){
        Pattern r = Pattern.compile(pattern);
        List<Point> points = new ArrayList<>();
        String[] splitedLine;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = r.matcher(line);
                if(m.find()){
                    splitedLine = line.split(" ");
                    points.add(new Point(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]), Integer.parseInt(splitedLine[2])));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}