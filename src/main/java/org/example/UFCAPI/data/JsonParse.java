package org.example.UFCAPI.data;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.example.UFCAPI.model.Event;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import java.util.ArrayList;



public class JsonParse {

    public ArrayList<Event> events;

    private final WebClient webClient;

    public JsonParse(String apiUrl, String apiKey) {
        events = new ArrayList<>();

        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey).build();
        this.events = new ArrayList<>();


    }

    public ArrayList<Event> fetchData(String endpoint) throws IOException, InterruptedException {
        Gson gson = new Gson();

        try{
            String jsonResponse = webClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (jsonResponse != null) {
                JsonReader jsonReader = new JsonReader(new StringReader(jsonResponse));
                jsonReader.setLenient(true);

                Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
                this.events = gson.fromJson(jsonReader, listType);
            }
        }catch (WebClientException e){
            System.out.println("WebClientException: " + e.getMessage());
        }
        events.forEach(System.out::println);
        return events;
    }

}
