package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;

public interface ISymptomWriter {

    /**
     *
     * @param Map <key : symptom, value : number of occurrencies>
     * @throws IOException
     */
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException;
}
