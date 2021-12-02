package com.accountoverview.graphical;

public interface AppConstants
{
	public static final String DATE_FORMAT = "MM/dd/yyy";

	enum TranType
	{
		Dr, Cr
	}

	enum TranDetail
	{
		Income, Entertainment, Transfers, Bills, Groceries, Miscellaneous, Shopping

	}
}
