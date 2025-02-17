package Parsers;

import RTreesF3.Entities.Hedge;
import RTreesF3.Entities.RTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DatasetLoaderF3 {

    private static final String parseSeparator = ";";

    private static int hedgeLinesNum;
    //private static final int EARTH_RADIUS_KM = 6371;

    public static int getHedgeLinesNum (){
        return hedgeLinesNum;
    }

    private static Hedge csvLineToHedge(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        //double x = EARTH_RADIUS_KM * Double.parseDouble(field[2]);
        //double y = EARTH_RADIUS_KM * Double.parseDouble(field[3]);

        double x = Double.parseDouble(field[3]);
        double y = Double.parseDouble(field[2]);

        return new Hedge(field[0], Float.parseFloat(field[1]), y, x, field[4]);
    }

    public static void loadHedges(String pathName, RTree rTree) {

        Hedge hedge;

        Path path = Path.of(pathName);

        try {

            List<String> csvLines = Files.readAllLines(path);

            hedgeLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= hedgeLinesNum; i++) {
                hedge = (csvLineToHedge(csvLines.get(i)));

                rTree.addHedge(hedge);   //As we are reading the dataset, we create the RTree structure
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
