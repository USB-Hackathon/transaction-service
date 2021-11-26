package com.accountoverview.graphical.model;

import java.time.LocalDate;

import com.accountoverview.graphical.AppConstants.TranDetail;
import com.accountoverview.graphical.AppConstants.TranType;

public class TransactionVO
{
	private String date;

	private LocalDate tranDate;

	private TranType type;

	private TranDetail detail;

	private Integer amount;

	private String accountnum;

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public TranType getType()
	{
		return type;
	}

	public void setType(TranType type)
	{
		this.type = type;
	}

	public TranDetail getDetail()
	{
		return detail;
	}

	public void setDetail(TranDetail detail)
	{
		this.detail = detail;
	}

	public Integer getAmount()
	{
		return amount;
	}

	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	public LocalDate getTranDate()
	{
		return tranDate;
	}

	public void setTranDate(LocalDate tranDate)
	{
		this.tranDate = tranDate;
	}

	public String getAccountnum()
	{
		return accountnum;
	}

	public void setAccountnum(String accountnum)
	{
		this.accountnum = accountnum;
	}

}
