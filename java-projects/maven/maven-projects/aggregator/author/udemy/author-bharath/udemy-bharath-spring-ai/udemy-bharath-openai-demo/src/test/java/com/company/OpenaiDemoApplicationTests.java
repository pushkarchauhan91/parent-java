package com.company;

import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(properties = "spring.ai.openai.api-key=test-key")
class OpenaiDemoApplicationTests {

	// Mocked so the context loads without Pinecone (and DataInitializer doesn't call OpenAI for embeddings)
	@MockitoBean
	private VectorStore vectorStore;

	@Test
	void contextLoads() {
	}

}
