package com.accountoverview.graphical.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionOverviewResponseVO
{
	private Integer totalNoEarnings = 0;

	private Integer totalNoExpense = 0;

	private Integer totalNoAccounts = 0;

	private List<TransactionDetailResponseVO> transactions;

	public Integer getTotalNoEarnings()
	{
		return totalNoEarnings;
	}

	public void setTotalNoEarnings(Integer totalNoEarnings)
	{
		this.totalNoEarnings = totalNoEarnings;
	}

	public Integer getTotalNoExpense()
	{
		return totalNoExpense;
	}

	public void setTotalNoExpense(Integer totalNoExpense)
	{
		this.totalNoExpense = totalNoExpense;
	}

	public Integer getTotalNoAccounts()
	{
		return totalNoAccounts;
	}

	public void setTotalNoAccounts(Integer totalNoAccounts)
	{
		this.totalNoAccounts = totalNoAccounts;
	}

	public List<TransactionDetailResponseVO> getTransactions()
	{
		if(transactions == null)
			transactions = new ArrayList<>();
		return transactions;
	}
}
