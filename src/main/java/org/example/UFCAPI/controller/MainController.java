package org.example.UFCAPI.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.UFCAPI.data.JsonParse;
import org.example.UFCAPI.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


// i hope this works
@RestController
@RequestMapping(path = "/API/", produces = "application/json")
@CrossOrigin(origins = "*")
public class MainController {
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("API_KEY");


    @GetMapping("/{apiKey}/{year}/any/any")
    public ResponseEntity<List<Event>> getYear(@PathVariable("year") String year) {
        String url = "https://fightingtomatoes.com/API/" + apiKey;

        JsonParse jsonParse = new JsonParse(url, apiKey);
        try {
            List<Event> events = jsonParse.fetchData(url);
            return new ResponseEntity<>(events, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{apiKey}/any/{event}/fighter")
    public ResponseEntity<List<Event>> getEvent(@PathVariable("event") String event) throws IOException, InterruptedException {

        String url = "https://fightingtomatoes.com/API/" + apiKey;
        JsonParse jsonParse = new JsonParse(url, apiKey);
        try {
            List<Event> events = jsonParse.fetchData(url);

            return ResponseEntity.status(HttpStatus.OK).body(events);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{apiKey}/any/any/{fighter}")
    public ResponseEntity<List<Event>> getFighter(@PathVariable("fighter") String fighter, @PathVariable String apiKey) throws IOException, InterruptedException {
        String url = "https://fightingtomatoes.com/API/" + apiKey;
        JsonParse jsonParse = new JsonParse(url, apiKey);
        try {
            List<Event> events = jsonParse.fetchData(url);
            return ResponseEntity.ok(events);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
