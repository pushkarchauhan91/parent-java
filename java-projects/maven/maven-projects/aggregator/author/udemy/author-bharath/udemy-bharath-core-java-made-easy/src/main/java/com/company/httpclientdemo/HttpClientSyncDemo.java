package com.company.httpclientdemo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientSyncDemo {

    private static final URI ACTIVITIES_URI = URI.create("https://fakerestapi.azurewebsites.net/api/v1/Activities");

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder(ACTIVITIES_URI)
                .GET().build();
        printResponse(client.send(getRequest, BodyHandlers.ofString()));

        HttpRequest postRequest = HttpRequest.newBuilder(ACTIVITIES_URI)
                .POST(BodyPublishers.ofString("{\n" + " \"id\":0,\n" + " \"title\":\"string\",\n" +
                        " \"dueDate\":\"2020-06-24T08:32:49.249Z\",\n" + " \"completed\": true\n" + "}"))
                .header("Content-Type", "application/json").build();
        printResponse(client.send(postRequest, BodyHandlers.ofString()));

    }

    private static void printResponse(HttpResponse<String> response) {
        System.out.println("Status===>" + response.statusCode());
        System.out.println(response.body());
        HttpHeaders headers = response.headers();
        System.out.println(headers.map());
    }

}
