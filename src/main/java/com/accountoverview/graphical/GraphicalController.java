package com.accountoverview.graphical;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountinfo")
public class GraphicalController
{
	@GetMapping("/ping")
	public String ping()
	{
		return "Hello world USBank!";
	}

}
