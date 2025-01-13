package com.hemebiotech.analytics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class will read a file, count occurrences of three symptoms ("headache", "rash", "pupils"),
 *  and write the results in an output file.
 */
public class AnalyticsCounter {

    private static Logger logger = LogManager.getLogger(AnalyticsCounter.class);
    // Initialize counters for each symptom
    static int headacheCount = 0;	// counts headache occurencies
    static int  rashCount =0; // counts rash occurencies
    static int  pupilCount = 0; // counts dialated pupils occurencies

    public static void main(String args[]) throws Exception {

        // Specify the input and output files
        String inputFile = "symptoms.txt";
        String outputFile = "result.out";


        //Step 1 : Read symtoms from input file
        try {
            ISymptomReader reader = new ReadSymptomDataFromFile(inputFile);
            List<String> symptoms = reader.getSymptoms();

            // Step 2: Count occurrences of each symptom
            for (String symptom : symptoms) {
                if (symptom.equals("headache")) {
                    headacheCount++;
                } else if (symptom.equals("rash")) {
                    rashCount++;
                } else if (symptom.contains("dialated pupils")) {
                    pupilCount++;

                }
            }
        } catch (IOException e) {
            logger.error("Erreur de Input File");
        }
        //TO DO Step 3 : Order by alphabatically

        // TO DO Step 4 : write result to the output file
       try{
           FileWriter writer = new FileWriter(outputFile);
           writer.write("headache: " + headacheCount + "\n");
           writer.write("rash: " + rashCount + "\n");
           writer.write("dialated pupils: " + pupilCount);
           writer.close();
       }catch(IOException e) {
           logger.error("Erreur de Output File");
       }

    }
}
