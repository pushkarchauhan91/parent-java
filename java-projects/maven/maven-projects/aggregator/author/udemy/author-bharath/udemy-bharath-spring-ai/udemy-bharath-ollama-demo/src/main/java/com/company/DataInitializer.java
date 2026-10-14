package com.company;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
	
	private final VectorStore vectorStore;

	public DataInitializer(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	@PostConstruct
	public void init() {
		TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
				.withChunkSize(100)
				.withMinChunkSizeChars(100)
				.withMinChunkLengthToEmbed(5)
				.withMaxNumChunks(1000)
				.withKeepSeparator(true)
				.build();
		
		TextReader productDataReader = new TextReader(new ClassPathResource("product-data.txt"));
		List<Document> documents = tokenTextSplitter.split(productDataReader.get());
		vectorStore.add(documents);
		
	}

}
