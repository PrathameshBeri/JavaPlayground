package PCProblem;

public abstract class Ingredient {

	private String name;
	private String color;
	private String taste;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public Ingredient(String name, String color, String taste) {
		super();
		this.name = name;
		this.color = color;
		this.taste = taste;
	}

	public String getDescription() {
		String description = "Ingredient is a " + this.getName() + "its color is " + this.getColor() + "its taste is "
				+ this.getTaste();
		return description;
	}
	
}
