package PCProblem;

public interface IngredientLine {
	
	
	public void blockingPut(Ingredient i) throws InterruptedException;
	
	public Ingredient blockingGet() throws InterruptedException;

}
