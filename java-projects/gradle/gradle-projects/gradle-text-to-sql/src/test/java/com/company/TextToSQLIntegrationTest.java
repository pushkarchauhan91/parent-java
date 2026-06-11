//package com.company;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(
//        webEnvironment =
//                SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//class TextToSQLIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    private final TestRestTemplate restTemplate =
//            new TestRestTemplate();
//
//    private final ObjectMapper objectMapper =
//            new ObjectMapper();
//
//    @Test
//    void shouldGenerateSqlAndReturnCount() throws Exception {
//
//        String url =
//                "http://localhost:"
//                        + port
//                        + "/api/text-to-sql";
//
//        String requestBody = """
//                {
//                  "question": "return count of records from orderdelivery where ordercode starting with uat top 10"
//                }
//                """;
//
//        HttpHeaders headers =
//                new HttpHeaders();
//
//        headers.setContentType(
//                MediaType.APPLICATION_JSON
//        );
//
//        HttpEntity<String> request =
//                new HttpEntity<>(
//                        requestBody,
//                        headers
//                );
//
//        ResponseEntity<String> response =
//                restTemplate.exchange(
//                        url,
//                        HttpMethod.POST,
//                        request,
//                        String.class
//                );
//
//        assertEquals(
//                HttpStatus.OK,
//                response.getStatusCode()
//        );
//
//        JsonNode jsonData =
//                objectMapper.readTree(
//                        response.getBody()
//                );
//
//        assertNotNull(
//                jsonData.get("generatedSql")
//        );
//
//        String generatedSql =
//                jsonData
//                        .get("generatedSql")
//                        .asText()
//                        .toLowerCase();
//
//        assertTrue(
//                generatedSql.contains("select count(*)")
//        );
//
//        assertTrue(
//                generatedSql.matches(
//                        ".*from\\s+orders\\.orderdelivery.*"
//                )
//        );
//
//        assertTrue(
//                generatedSql.contains(
//                        "ordercode like 'uat%'"
//                )
//        );
//
//        assertTrue(
//                generatedSql.contains("limit 10")
//        );
//
//        JsonNode rows =
//                jsonData.get("rows");
//
//        assertTrue(rows.isArray());
//
//        assertTrue(rows.size() > 0);
//
//        JsonNode firstRow =
//                rows.get(0);
//
//        assertTrue(
//                firstRow.has("count")
//        );
//
//        int count =
//                firstRow
//                        .get("count")
//                        .asInt();
//
//        assertEquals(
//                32286,
//                count
//        );
//    }
//
//    @Test
//    void shouldGenerateSqlAndReturnCountWithLimit100()
//            throws Exception {
//
//        String url =
//                "http://localhost:"
//                        + port
//                        + "/api/text-to-sql";
//
//        String requestBody = """
//                {
//                  "question": "return count of records from orderdelivery"
//                }
//                """;
//
//        HttpHeaders headers =
//                new HttpHeaders();
//
//        headers.setContentType(
//                MediaType.APPLICATION_JSON
//        );
//
//        HttpEntity<String> request =
//                new HttpEntity<>(
//                        requestBody,
//                        headers
//                );
//
//        ResponseEntity<String> response =
//                restTemplate.exchange(
//                        url,
//                        HttpMethod.POST,
//                        request,
//                        String.class
//                );
//
//        assertEquals(
//                HttpStatus.OK,
//                response.getStatusCode()
//        );
//
//        JsonNode jsonData =
//                objectMapper.readTree(
//                        response.getBody()
//                );
//
//        assertNotNull(
//                jsonData.get("generatedSql")
//        );
//
//        String generatedSql =
//                jsonData
//                        .get("generatedSql")
//                        .asText()
//                        .toLowerCase();
//
//        assertTrue(
//                generatedSql.contains("select count(*)")
//        );
//
//        assertTrue(
//                generatedSql.matches(
//                        ".*from\\s+orders\\.orderdelivery.*"
//                )
//        );
//
//        JsonNode rows =
//                jsonData.get("rows");
//
//        assertTrue(
//                rows.isArray()
//        );
//
//        assertFalse(
//                rows.isEmpty()
//        );
//
//        JsonNode firstRow =
//                rows.get(0);
//
//        assertTrue(
//                firstRow.has("count")
//        );
//
//        assertTrue(
//                firstRow.get("count").isNumber()
//        );
//
//        int count =
//                firstRow
//                        .get("count")
//                        .asInt();
//
//        assertEquals(
//                153091,
//                count
//        );
//    }
//}