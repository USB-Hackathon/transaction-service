package com.accountoverview.graphical;

import static com.accountoverview.graphical.AppConstants.DATE_FORMAT;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountoverview.graphical.AppConstants.TranType;
import com.accountoverview.graphical.model.TransactionByMonthResponseVO;
import com.accountoverview.graphical.model.TransactionDetailByMonthResponseVO;
import com.accountoverview.graphical.model.TransactionVO;
import com.accountoverview.graphical.model.vo.TransactionBreakupVO;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
	@GetMapping("/ping")
	public String ping()
	{
		return "Hello world: Transaction records!";
	}

	@GetMapping("/list")
	public ResponseEntity<List<TransactionVO>> getAllTransactions() throws Exception
	{

		List<TransactionVO> tranList = getRecords();
		return new ResponseEntity<List<TransactionVO>>(tranList, HttpStatus.OK);
	}

	@GetMapping("/year/{year}")
	public ResponseEntity<List<TransactionByMonthResponseVO>> getTransactionsByYear(@PathVariable Integer year)
			throws Exception
	{

		List<TransactionVO> tranList = getRecords();

		tranList.removeIf(t -> t.getTranDate().getYear() != year);

		Map<Integer, TransactionByMonthResponseVO> monthlyTransactionResponseVOs = new HashMap<>();

		for(TransactionVO tranVO : tranList)
		{
			int monthKey = tranVO.getTranDate().getMonthValue();

			TransactionByMonthResponseVO mVO = monthlyTransactionResponseVOs.getOrDefault(monthKey,
					new TransactionByMonthResponseVO());

			mVO.setMonth(tranVO.getTranDate().getMonth().name());
			mVO.setExpense(mVO.getExpense() + (tranVO.getType() == TranType.Dr ? tranVO.getAmount() : 0));
			mVO.setEarning(mVO.getEarning() + (tranVO.getType() == TranType.Cr ? tranVO.getAmount() : 0));

			monthlyTransactionResponseVOs.put(monthKey, mVO);
		}

		return new ResponseEntity<List<TransactionByMonthResponseVO>>(
				monthlyTransactionResponseVOs.values().stream().collect(Collectors.toList()), HttpStatus.OK);

	}

	@GetMapping("/monthyear/{monthYear}")
	public ResponseEntity<TransactionDetailByMonthResponseVO> getTransactionsByMonthYear(
			@PathVariable Integer monthYear) throws Exception
	{

		Integer month = monthYear / 100;
		Integer year = 2000 + (monthYear % 100);

		List<TransactionVO> tranList = getRecords();

		tranList.removeIf(t -> t.getTranDate().getYear() != year);
		tranList.removeIf(t -> t.getTranDate().getMonthValue() != month);

		TransactionDetailByMonthResponseVO monthResponseVO = new TransactionDetailByMonthResponseVO();

		for(TransactionVO tranVO : tranList)
		{
			TransactionBreakupVO breakVO = new TransactionBreakupVO();
			breakVO.setLabel(tranVO.getDetail().name());
			breakVO.setValue(Integer.toString(tranVO.getAmount()));

			if(tranVO.getType() == TranType.Dr)
				monthResponseVO.getExpenses().add(breakVO);

			else if(tranVO.getType() == TranType.Cr)
				monthResponseVO.getEarnings().add(breakVO);
		}

		return new ResponseEntity<TransactionDetailByMonthResponseVO>(monthResponseVO, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	private List<TransactionVO> getRecords() throws ParseException
	{
		File input = new File("src/main/resources/TransactionData.csv");
		List<Map<?, ?>> list = null;

		try
		{
			CsvSchema csv = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();

			MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader().forType(TransactionVO.class).with(csv)
					.readValues(input);
			list = mappingIterator.readAll();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		List<TransactionVO> tranList = new ArrayList<TransactionVO>();
		tranList.addAll((List<? extends TransactionVO>)list);

		DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);

		for(TransactionVO tranVO : tranList)
			tranVO.setTranDate(LocalDate.parse(tranVO.getDate(), dtfInput));

		return tranList;
	}
}
