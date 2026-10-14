package com.company.text;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.services.OpenAiService;

import reactor.core.publisher.Flux;

@RestController
public class AnswerAnyThingStreamingController {

	private final OpenAiService service;

	public AnswerAnyThingStreamingController(OpenAiService service) {
		this.service = service;
	}

	@GetMapping("/stream")
	public Flux<ChatResponse> askAnything(@RequestParam String message) {
		return service.streamAnswer(message);
	}
}