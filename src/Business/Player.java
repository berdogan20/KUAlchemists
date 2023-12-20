package Business;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String userName;
	private String avatarPath;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private List<Artifact> artifacts = new ArrayList<Artifact>();
	private int balance;
	private int reputationPoints;
	private DeductionBoard deductionBoard = new DeductionBoard();
	private int sicknessLevel;
	private int goldtToBePayedToArtifact;
	
	public Player(String userName, String avatarPath) {
		
		this.userName = userName;
		this.avatarPath = avatarPath;
		this.goldtToBePayedToArtifact = -3;
	}
	
	public Player(String userName, String avatarPath, List<Ingredient> ingredients, List<Artifact> artifacts,
		int balance, int reputationPoints, DeductionBoard deductionBoard) {
		this.userName = userName;
		this.avatarPath = avatarPath;
		this.ingredients = ingredients;
		this.artifacts = artifacts;
		this.balance = balance;
		this.reputationPoints = reputationPoints;
		this.deductionBoard = deductionBoard;
		this.goldtToBePayedToArtifact = -3;
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		ingredients.add(ingredient);
	}
	


	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getAvatarPath() {
		return avatarPath;
	}




	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}




	public List<Ingredient> getIngredients() {
		return ingredients;
	}




	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}




	public List<Artifact> getArtifacts() {
		return artifacts;
	}




	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}




	public int getBalance() {
		return balance;
	}




	public void setBalance(int balance) {
		this.balance = balance;
	}




	public int getReputationPoints() {
		return reputationPoints;
	}




	public void setReputationPoints(int reputationPoints) {
		this.reputationPoints = reputationPoints;
	}




	public DeductionBoard getDeductionBoard() {
		return deductionBoard;
	}




	public void setDeductionBoard(DeductionBoard deductionBoard) {
		this.deductionBoard = deductionBoard;
	}
	
	
	
	public int getSicknessLevel() {
		return sicknessLevel;
	}

	
	
	public void setSicknessLevel(int sicknessLevel) {
		this.sicknessLevel = sicknessLevel;
	}
	
	public int getGoldtToBePayedToArtifact() {
		return goldtToBePayedToArtifact;
	}

	public void setGoldtToBePayedToArtifact(int goldtToBePayedToArtifact) {
		this.goldtToBePayedToArtifact = goldtToBePayedToArtifact;
	}
	

	public void makeExperiment(Player currentPlayer, Ingredient ingr1, Ingredient ingr2, String whereToTest) {
		Experiment experiment = new Experiment(currentPlayer, ingr1, ingr2, whereToTest);
		this.removeIngredientCard(ingr1);
		this.removeIngredientCard(ingr2);
		GameEvent event = new GameEvent(null, this, GameEvent.EventID.MAKE_EXPERIMENT);
	}
	
	
	
	public void updateBalance(int amount) {
		setBalance(getBalance() + amount);
	}
	
	
	// I made it public so that I can use it in KUAlchGame to get the winner. And made it return float.
	public float calculateScore() {
		int score = 0;
		score += getReputationPoints() * 10;
		// 1 artifact -> 2 gold, 3 gold -> 1 score point => 1 artifact -> 2/3 score point.
		score += getArtifacts().size() * 2 / 3; 
		return score;
	}
	
	public String forageForIngredient() {
		
		Ingredient foragedIngredient = IngredientStorage.getInstance().getIngredient();
		if(foragedIngredient != null) {
			System.out.println("Previous ingredients");
			getIngredients().forEach(System.out::println);
			
			addIngredientCard(foragedIngredient);
			
			System.out.println("New ingredients");
			getIngredients().forEach(System.out::println);
			
			System.out.printf("Ingredient %s is added to the player's storage%n",foragedIngredient.getName());
			GameEvent events = new GameEvent(null, this, GameEvent.EventID.FORAGE_INGREDIENT);
			
			return foragedIngredient.getName();
			
		}
		else {
			System.out.println("Ingredient Storage is empty!");
			return null;
		}
	}
	
	public String transmuteIngredient(Ingredient ingredient) {
		
		System.out.println("");
		System.out.println("Previous ingredients");
		getIngredients().forEach(System.out::println);
	    System.out.printf("Old Balance: %d%n",getBalance());
	    
		if(removeIngredientCard(ingredient)) {									
			updateBalance(1);
			IngredientStorage.getInstance().addToBottom(ingredient);
						
		}
		else {
			throw new IllegalArgumentException();
		}
		
		System.out.println("New ingredients");
		getIngredients().forEach(System.out::println);
		System.out.printf("New Balance: %d%n",getBalance());
		System.out.printf("Ingredient %s is removed from the player's storage%n",ingredient.getName());
		GameEvent events = new GameEvent(null, this, GameEvent.EventID.TRANSMUTE_INGREDIENT);
		
		return ingredient.getName();
	}
	
	public String buyArtifact() {
		
		if(getBalance() >= -getGoldtToBePayedToArtifact()) {
			
			Artifact artifact = ArtifactStorage.getArtifact();
			if(artifact != null) {
				
				System.out.println();
				System.out.println("Previous artifacts");
				getArtifacts().forEach(System.out::println);
			    System.out.printf("Old Balance: %d%n",getBalance());
			    
				addArtifact(artifact);
				updateBalance(getGoldtToBePayedToArtifact());
				applyArtifact(artifact);
				
				System.out.println("New artifacts");
				getArtifacts().forEach(System.out::println);
				System.out.printf("New Balance: %d%n",getBalance());
				System.out.printf("Artifact %s is added to the player's storage%n",artifact.getName());
				GameEvent events = new GameEvent(null, this, GameEvent.EventID.BUY_ARTIFACT);
				
				return artifact.getName();
			}
			else {
				System.out.println("Artifact Storage is empty!");
			}
		}
		else {
			System.out.println("Balance is unsufficient, come back when you have more gold :D");
		}
		
		return null;
	}
	

	public void applyArtifact(Artifact artifact) {
		artifact.applyArtifact(this);
	}
	
	public void sellPotion(String potionName) {
		/*Potion potion = new Potion(potionName);
		int enumeratedPotionResult = enumeratePotionResult(potion);
		int enumeratedPromise = enumeratePromises(promise);
		if(enumeratedPotionResult < enumeratedPromise ) {
			System.out.println("Your promise does not satisfy potion result. Balance is unchanged.");
		}
		else {
			updateBalance(enumeratedPromise);
			System.out.printf("Your promise satisfied the potion result. You will be awarded by %d gold",enumeratedPromise);
		}
		*/
		
	}
	
	private void updateReputation(int amount) {
		setReputationPoints(getReputationPoints() + amount);
	}	
	
	private void addIngredientCard(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
		
	
	private boolean removeIngredientCard(Ingredient ingredient) {
		return getIngredients().remove(ingredient);
	}
		
		
	private void addArtifact(Artifact artifact) {
		getArtifacts().add(artifact);
	}
	
	private int enumeratePromises(String promise) {
		int return_val = 0;
		
		switch(promise) {
		case "Positive":
			return_val = 3;
			break;
		case "Positive or Neutral":
			return_val = 2;
			break;
		case "Nothing":
			return_val = 1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		return return_val;
	}
	
	/*private int enumeratePotionResult(Potion potion) {
		int return_val = 0;
		
		switch(potion.getDominantAspect().getSign()) {
		case "positive":
			return_val = 3;
			break;
		case "neutral":
			return_val = 2;
			break;
		case "negative":
			return_val = 1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		return return_val;
	}
		*/			
		
	
	
		
	private void publishTheory() {
	
		if (this.getBalance() < 1) {
			System.out.println("Insufficient balance to publish a theory");
		}
		
		
	}
		
	
	private void debunkTheory() {
		
	}
	
	
	private void putTokenToResultsTriangle() {
	
		
	}

	
	
	


}
