package PCProblem;

public class Vegetable extends Ingredient {


	public Vegetable(String name, String color, String taste) {
		super(name, color, taste);	
	}

	@Override
	public String getDescription() {
		String description = "Vegetable is a " + this.getName() + " its color is " + this.getColor() + " its taste is "
				+ this.getTaste();
		return description;
	}

}
