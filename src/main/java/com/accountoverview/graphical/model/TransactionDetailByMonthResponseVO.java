package com.accountoverview.graphical.model;

import java.util.ArrayList;
import java.util.List;

import com.accountoverview.graphical.model.vo.TransactionBreakupVO;

public class TransactionDetailByMonthResponseVO
{
	private List<TransactionBreakupVO> earnings;

	private List<TransactionBreakupVO> expenses;

	public List<TransactionBreakupVO> getEarnings()
	{
		if(earnings == null)
			earnings = new ArrayList<>();

		return earnings;
	}

	public List<TransactionBreakupVO> getExpenses()
	{
		if(expenses == null)
			expenses = new ArrayList<>();

		return expenses;
	}
}
