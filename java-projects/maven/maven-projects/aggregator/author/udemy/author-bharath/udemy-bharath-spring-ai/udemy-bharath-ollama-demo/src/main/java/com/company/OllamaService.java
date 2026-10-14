package com.company;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {
	
	private final ChatClient chatClient;
	
	private final VectorStore vectorStore;
	
	public OllamaService(ChatClient.Builder builder, ChatMemory chatMemory, VectorStore vectorStore) {
		this.vectorStore = vectorStore;
		chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor(),MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
	}
	
	public ChatResponse generateAnswer(String question) {
		return chatClient.prompt(question).call().chatResponse();
	}
	

	public String answer(String query) {
		return chatClient.prompt("Use the context below and answer the following user question "+ query).advisors(QuestionAnswerAdvisor.builder(vectorStore).build()).call().content();
	}
	

}
