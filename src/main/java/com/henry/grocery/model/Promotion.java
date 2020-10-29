package com.henry.grocery.model;

/**
 * Promotion class stores the product id, the Promotion name, discount price,
 * promotion start date and promotion end date
 * 
 */
public class Promotion {

	private long id;
	private String name;
	private float price;
	private String startDate;
	private String endDate;
	private long discountId;
	private int eligibleCount;

	public Promotion(long id, String name, float price, String startDate, String endDate, long discountId, int eligibleCount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountId = discountId;
		this.eligibleCount = eligibleCount;
	}

	public long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public int getEligibleCount() {
		return eligibleCount;
	}

	public void setEligibleCount(int eligibleCount) {
		this.eligibleCount = eligibleCount;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", name=" + name + ", price=" + price + ", startDate=" + startDate + ", endDate="
				+ endDate + ", discountId=" + discountId + ", eligibleCount=" + eligibleCount + "]";
	}

}
