package com.zed.foodrecipes.data;

import java.util.List;

import com.zed.foodrecipes.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ImportCsvDataFromRecipe extends ImportCsvData{
	
	private final static String FILE_PATH = "C:/DEV/repo/FoodRecipesServer/data/recipes/input.csv";
	private final static String SEPARATOR = ";";
	private final static String COLLECTION_NAME = "recipe";
	
    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ImportCsvDataFromRecipe.class);
    
    public ImportCsvDataFromRecipe(MongoTemplate mongoTemplate) {
    	super(mongoTemplate);
	}
    
	@Override
	protected void createAndInsertModel(List<String> lineSplitted) {
        Recipe recipe = Recipe.createRecipe(lineSplitted);
        mongoTemplate.insert(recipe);
        logger.info("Insert: " + recipe);
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
