package system;

public class DishOrder {
	
	int dishID;
	int amount;
	String comments;
	
	public DishOrder(int dishID, int amount, String comments){
		this.dishID = dishID;
		this.amount = amount;
		this.comments = comments;
	}

	public int getDishID() {
		return dishID;
	}

	public void setDishID(int dishID) {
		this.dishID = dishID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	

}
