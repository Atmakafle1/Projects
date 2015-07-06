package edu.gatech.seclass.prj2;

public class Reward {

	private double amount;
	private double threshold;
	private String custId;
	
	public Reward(double amount, double threshold, String custId) {
    super();
    this.amount = amount;
    this.threshold = threshold;
    this.custId = custId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getThreshold() {
    return threshold;
  }

  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
}
