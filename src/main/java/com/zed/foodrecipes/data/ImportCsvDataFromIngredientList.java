package com.zed.foodrecipes.data;

import com.zed.foodrecipes.model.Recipe;
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

public class ImportCsvDataFromIngredientList extends ImportCsvData{

	private final static String FILE_PATH = "C:/DEV/repo/FoodRecipesServer/data/recipes/ingredients.csv";
	private final static String SEPARATOR = ";";
	private final static String COLLECTION_NAME = "recipe";

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ImportCsvDataFromIngredientList.class);

    public ImportCsvDataFromIngredientList(MongoTemplate mongoTemplate) {
    	super(mongoTemplate);
	}

    @Override
    public void insertDataIntoDBFromCsvFile() {

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
    
	@Override
	protected void createAndInsertModel(List<String> lineSplitted) {

        String ingredient = lineSplitted.get(2);

        Recipe recipe = mongoTemplate.findById(lineSplitted.get(0), Recipe.class);
        recipe.getIngredients().add(ingredient);

        mongoTemplate.save(recipe);

        logger.info("Update recipe id[" + recipe + "] with ingredient ["+ingredient+"]");
	}    

	@Override
	protected String getFilePath() {
		return FILE_PATH;
	}


	@Override
	protected String getSeparator() {
		return SEPARATOR;
	}

	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}

}
