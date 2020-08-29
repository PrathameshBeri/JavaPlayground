package PCProblem;

public class Fruit extends Ingredient {


	public Fruit(String name, String color, String taste) {
		super(name, color, taste);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		String description = "Fruit is a " + this.getName() + " its color is " + this.getColor() + " its taste is "
				+ this.getTaste();
		return description;
	}

}
