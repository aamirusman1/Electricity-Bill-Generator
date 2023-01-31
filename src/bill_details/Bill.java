package bill_details;

import consumer_details.Consumer;

public class Bill {
	private int billId;
	private int consumerId;
	private int unitsConsumed;
	private int year;
	private String month;
	private int totalAmount;
	
	
	public Bill(int billId, int consumerId, int unitsConsumed, int year, String month, int totalAmount) {
		super();
		this.billId = billId;
		this.consumerId = consumerId;
		this.unitsConsumed = unitsConsumed;
		this.year = year;
		this.month = month;
		this.totalAmount = totalAmount;
	}
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public int getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
