package com.hemebiotech.analytics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class will read a file, count occurrences of three symptoms ("headache", "rash", "dialated pupils"),
 * and write the results in an output file.
 */
public class AnalyticsCounter {

    // Initialize counters for each symptom
    static int headacheCount = 0;    // counts headache occurencies
    static int rashCount = 0; // counts rash occurencies
    static int pupilCount = 0; // counts dialated pupils occurencies
    private static Logger logger = LogManager.getLogger(AnalyticsCounter.class);
    private ISymptomReader reader;
    private ISymptomWriter writer;

    /**
     * @param reader : read symtoms data
     * @param writer : write results like symptom : numberOfOccurences
     */
    public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * @return List<String> as each </String> is a symtom
     * @throws IOException
     */
    public List<String> getSymptoms() throws IOException {
        return reader.getSymptoms();
    }

    //Créez une méthode countSymptoms qui compte les occurrences de chaque symptôme existant

    /**
     *
     * @param symptoms
     * @return symtoms with occurrences not ordered yet
     */
    public Map<String, Integer> countSymptoms(List<String> symptoms) {
        if (symptoms.isEmpty())
            logger.error("Liste de symptômes vide, on ne peut compter les occurrences");

        Map<String, Integer> symtomsWithOccurrences = new HashMap<>();
        for (String symtom : symptoms) {
            if (symtomsWithOccurrences.containsKey(symtom)) {
                symtomsWithOccurrences.put(symtom, 1);
            } else {
                symtomsWithOccurrences.replace(symtom, symtomsWithOccurrences.get(symtom) + 1);
            }
        }
        return symtomsWithOccurrences;
    }

    //Créez une méthode sortSymptoms qui trie la liste de symptômes et d’occurrences par ordre alphabétique ;

    /**
     *
     * @param symptoms
     * @return symtoms with occurrences ordered alphabetically
     */
    public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
        Map<String, Integer> sortedSymtomsWithOccurrences = new TreeMap<>(symptoms);
        return sortedSymtomsWithOccurrences;
    }

    //Créez une méthode writeSymptoms qui écrit le résultat dans le fichier de sortie en utilisant
    // l’instance de ISymptomWriter déjà créée.

    /**
     *
     * @param symptoms
     * @throws IOException
     */
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
        writer.writeSymptoms(symptoms);
    }

    public static void main(String args[]) throws Exception {

        // Specify the input and output files
        String inputFile = "symptoms.txt";
        String outputFile = "result.out";

        //Step 1 : Read symtoms from an input file
        try {
            ISymptomReader reader = new ReadSymptomDataFromFile(inputFile);
            ISymptomWriter writer = new WriteSymptomDataToFile(outputFile);

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
        //TO DO Step 3 : Symtoms ordered alphabatically

        // TO DO Step 4 : write result to the output file
        try {
            FileWriter writer = new FileWriter(outputFile);
            writer.write("headache: " + headacheCount + "\n");
            writer.write("rash: " + rashCount + "\n");
            writer.write("dialated pupils: " + pupilCount);
            writer.close();
        } catch (IOException e) {
            logger.error("Erreur de Output File");
        }

    }


}
