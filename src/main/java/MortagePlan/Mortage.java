package MortagePlan;

public class Mortage {

	private final int PAYMENTS_IN_A_YEAR = 12;

	private String customer;
	private float monthlyInterest;
	private float totalLoan;
	private int years;
	private float monthlyPayment;

	public Mortage(String customer, float totalLoan, float monthlyInterest, int years) {
		this.customer = customer;
		this.monthlyInterest = monthlyInterest;
		this.totalLoan = totalLoan;
		this.years= years;
		
		updateMonthlyPayment();
	}

	private void updateMonthlyPayment()
	{
		float b = (float) ((this.monthlyInterest / 12.0)/100.0);
		float U = this.totalLoan;
		int p = this.years * PAYMENTS_IN_A_YEAR;
		
		this.monthlyPayment = (float) (U*(b*Math.pow(1+b,p))/(Math.pow(1+b,p)-1));
	}
	
	public float getMonthlyPayment() {return this.monthlyPayment; }
	public float getTotalLoan() {return this.totalLoan; }
	public int getYears() {return this.years; }
	public float getMonthlyInterest() {return this.monthlyInterest; }
	public String getCustomer() {return this.customer; }
}
