package com.rahul.SpringBootSportyShoe.Model;

public class PurchaseReport {
	private int productId;
	private int stock_cleared;

	public PurchaseReport(int productId, int stock_cleared) {
		super();
		this.productId = productId;
		this.stock_cleared = stock_cleared;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStock_cleared() {
		return stock_cleared;
	}

	public void setStock_cleared(int stock_cleared) {
		this.stock_cleared = stock_cleared;
	}

}
