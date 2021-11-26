package com.accountoverview.graphical.model;

public class TransactionDetailResponseVO
{
	private String label;

	private Integer value = 0;

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}
}
