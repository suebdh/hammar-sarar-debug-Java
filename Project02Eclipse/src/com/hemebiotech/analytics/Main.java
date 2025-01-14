package com.hemebiotech.analytics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        String filepath = "symptoms.txt";
        ISymptomReader reader = new ReadSymptomDataFromFile(filepath);
        ISymptomWriter writer = new WriteSymptomDataToFile();
        AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

        logger.info("Le programe chez Heme Biotech commence ...");
        //Step 1 : Read symptoms from an input file "symtoms.txt" and obtain list of symtoms
        List<String> symtoms = counter.getSymptoms();
        //Step 2 : Obtain symtoms with their occurrences
        Map<String, Integer> symtomsWithOccurrences = counter.countSymptoms(symtoms);
        //Step 3 : Order this list alphabetically
        Map<String, Integer> sortedSymtomsWithOccurrences = counter.sortSymptoms(symtomsWithOccurrences);
        //Step 4 : Write results in an outputfile "result.out"
        counter.writeSymptoms(sortedSymtomsWithOccurrences);
        logger.info("Et voilà, finit le programme, allez checker votre fichier result.txt ...");


    }
}
