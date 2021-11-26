package com.accountoverview.graphical.model;

public class TransactionByMonthResponseVO
{
	private String month;

	private Integer earning = 0;

	private Integer expense = 0;

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public Integer getEarning()
	{
		return earning;
	}

	public void setEarning(Integer earning)
	{
		this.earning = earning;
	}

	public Integer getExpense()
	{
		return expense;
	}

	public void setExpense(Integer expense)
	{
		this.expense = expense;
	}
}
