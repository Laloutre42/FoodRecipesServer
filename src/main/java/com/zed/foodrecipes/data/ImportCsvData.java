package com.zed.foodrecipes.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public abstract class ImportCsvData {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ImportCsvData.class);

    protected MongoTemplate mongoTemplate;

    public ImportCsvData(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public void insertDataIntoDBFromCsvFile() {

        // Delete old values
        mongoTemplate.dropCollection(getCollectionName());

        Path path = Paths.get(getFilePath());
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {

            int numLine = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                numLine++;

                // Skip first line
                if (numLine != 1) {
                    createAndInsertModel(Arrays.asList(line.split(getSeparator(), -1)));
                }
            }

        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }


    protected abstract void createAndInsertModel(List<String> lineSplitted);

    protected abstract String getFilePath();

    protected abstract String getSeparator();

    protected abstract String getCollectionName();

}
