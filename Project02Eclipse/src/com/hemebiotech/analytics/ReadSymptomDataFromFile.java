package com.hemebiotech.analytics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute force implementation
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

    private static Logger logger = LogManager.getLogger(ReadSymptomDataFromFile.class);
    private String filepath;

    /**
     * @param filepath a full or partial path to file with symptom strings in it, one per line
     */
    public ReadSymptomDataFromFile(String filepath) {

        this.filepath = filepath;
    }

    /**
     * This method reads the text file in entry line by line
     *
     * @return result : ArrayList containing all symtoms (possible duplicates)
     * @throws FileNotFoundException if the opening or reading of a file was failed ; IOException if erros of Input/output
     */
    @Override
    public List<String> getSymptoms() throws IOException {
        // ArrayList since the number of String can change dynamically
        ArrayList<String> result = new ArrayList<String>();

        if (filepath != null) {
            try {
                logger.info("Fichier d'entrée en lecture");
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                String line = reader.readLine();

                while (line != null) {
                    result.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                logger.error("Fichier non trouvé");
            } catch (IOException e) {
                logger.error("Erreur lors de la lecture du fichier" + e.getMessage());
            }
        }
        return result;
    }

}
