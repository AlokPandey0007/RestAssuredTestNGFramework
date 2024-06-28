package pojo;

public class CreateBookingPost {

	private String firstname;
	private String lastname;
	private long totalprice;
	private boolean depositpaid;
	private Bookingdates bookingdates;
	private String additionalneeds;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String value) {
		this.firstname = value;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String value) {
		this.lastname = value;
	}

	public long getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(long value) {
		this.totalprice = value;
	}

	public boolean getDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean value) {
		this.depositpaid = value;
	}

	public Bookingdates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(Bookingdates value) {
		this.bookingdates = value;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String value) {
		this.additionalneeds = value;
	}
	
	public String toString()
	{
		 return "CreateBookingPost{" +
	                "firstname='" + firstname + '\'' +
	                ", lastname='" + lastname + '\'' +
	                ", totalprice=" + totalprice +
	                ", depositpaid=" + depositpaid +
	                ", bookingdates=" + bookingdates.getCheckin() +"and " +bookingdates.getCheckout() +
	                ", additionalneeds='" + additionalneeds + '\'' +
	                '}';
	}
}
