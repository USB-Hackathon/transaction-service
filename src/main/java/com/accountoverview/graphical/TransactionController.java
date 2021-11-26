package com.accountoverview.graphical;

import static com.accountoverview.graphical.AppConstants.DATE_FORMAT;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountoverview.graphical.model.TransactionVO;
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
		return "Hello world!";
	}

	@GetMapping("/get")
	public ResponseEntity<List<TransactionVO>> getAccountSummary() throws Exception
	{

		List<TransactionVO> tranList = getRecords();
		return new ResponseEntity<List<TransactionVO>>(tranList, HttpStatus.OK);
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
