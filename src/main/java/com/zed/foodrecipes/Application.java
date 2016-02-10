package com.zed.foodrecipes;

import com.zed.foodrecipes.data.ImportCsvData;
import com.zed.foodrecipes.data.ImportCsvDataFromIngredientList;
import com.zed.foodrecipes.data.ImportCsvDataFromRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
//
//    @Value("${test}")
//    private String test;

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        if ((strings.length > 0) && (strings[0].equals("--import-data"))) {

            logger.debug("Loading data from csv file ... loading");

            ImportCsvData importCsvDataFromRecipe = new ImportCsvDataFromRecipe(mongoTemplate);
            importCsvDataFromRecipe.insertDataIntoDBFromCsvFile();

            ImportCsvData importCsvDataFromIngredientList = new ImportCsvDataFromIngredientList(mongoTemplate);
            importCsvDataFromIngredientList.insertDataIntoDBFromCsvFile();
            logger.debug("Loading data from csv file ... done");
        }
    }

}
