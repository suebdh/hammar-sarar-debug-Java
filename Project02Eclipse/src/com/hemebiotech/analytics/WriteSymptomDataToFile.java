package com.hemebiotech.analytics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {

    private static Logger log = LogManager.getLogger(WriteSymptomDataToFile.class);

    String outputFile = "result.out";

    @Override
    /**
     * This method write symptoms with their quantities in the file result.out .
     */
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {

        if (symptoms.isEmpty()) {
            log.error("Collection vide, il n y a rien à écrire !");
        } else {
           try{
               FileWriter writer = new FileWriter(outputFile);
               for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                   writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
               }
               writer.close();
           } catch (IOException e) {
               log.error("Problème d'écrirture dans le fichier");
           }
        }

    }
}
