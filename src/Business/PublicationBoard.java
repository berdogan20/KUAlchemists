package Business;

public class PublicationBoard 
{
	private static PublicationBoard instance;
	
	public static PublicationBoard getInstance() {
		if (instance == null) {
			instance = new PublicationBoard();
		}
		return instance;
	}

	public Theory publishTheory(Player owner, Token alchemyMarker, Ingredient ingredient) {

		if( owner.getBalance() >= 1) {
			
			if(Theory.getTheory(ingredient.getName()) != null) {
				System.out.println("This ingredient already have a theory on it.");
			}
			else {
				
				// create theory if not already exist
				Theory theory = new Theory(owner, alchemyMarker, ingredient);
				owner.updateBalance(owner.getPublishTheoryCharge());
				owner.setReputationPoints(owner.getReputationPoints() + owner.getGainedReputationPointWhilePublishing());
				owner.getTheories().add(theory);
				return theory;
			}
			
		}

		return null;
	}

	public Theory chooseTheory(String ingredientName) {
		
		Theory theory = null;
		for(Theory t: Theory.getAllTheories()) {
			if(ingredientName.equals(t.getIngredient().getName())) {
				theory = t;
			}
		}
		return theory;

	}
	
	public boolean debunkTheory(Theory selectedTheory, String selectedAspect) {
		
		if (selectedTheory == null || selectedAspect == null)
		{
			return false;
		}
		
		boolean comparaison = false;
		
		String actualToken = selectedTheory.getIngredient().getToken().getName();
		
		// if debunk is correct
		if (!actualToken.contains(selectedAspect)) {
			comparaison = true;
			Theory.getAllTheories().remove(selectedTheory);
			Theory.getDebunkedTheories().put(selectedTheory, false);
			selectedTheory.getOwner().setReputationPoints(selectedTheory.getOwner().getReputationPoints() - 1);
		}
		
		// if debunk is wrong
		if (actualToken.contains(selectedAspect)) {
			comparaison = false;
		}
		
		return comparaison;
		
	}
}
