package com.accountoverview.graphical.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionOverviewResponseVO {
	private Float totalEarnings = 0.0f;

	private Float totalExpenses = 0.0f;

	private Integer totalNoAccounts = 0;

	private List<TransactionDetailResponseVO> transactions;

	public Float getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(Float totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public Float getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Float totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Integer getTotalNoAccounts() {
		return totalNoAccounts;
	}

	public void setTotalNoAccounts(Integer totalNoAccounts) {
		this.totalNoAccounts = totalNoAccounts;
	}

	public List<TransactionDetailResponseVO> getTransactions() {
		if (transactions == null)
			transactions = new ArrayList<>();
		return transactions;
	}
}
