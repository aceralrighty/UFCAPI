package org.example.UFCAPI.model;
import lombok.Data;



@Data
public class Event {
    private String date;
    private String promotion;
    private String event;
    private String main_or_prelim;
    private int card_placement;
    private String fighter_1;
    private String fighter_2;
    private int rematch;
    private String winner;
    private String method;
    private int round;
    private String time;
    private int fighting_tomatoes_aggregate_rating;
    private int fighting_tomatoes_number_ratings;

    @Override
    public String toString() {
        return String.format(
                """
                        Event Details:
                        Date: %s
                        Promotion: %s
                        Event: %s
                        Main/Prelim: %s
                        Card Placement: %d
                        Fighter 1: %s
                        Fighter 2: %s
                        Rematch: %d
                        Winner: %s
                        Method: %s
                        Round: %d
                        Time: %s
                        Aggregate Rating: %d
                        Number of Ratings: %d
                        """,
                date,
                promotion,
                event,
                main_or_prelim,
                card_placement,
                fighter_1,
                fighter_2,
                rematch,
                winner,
                method,
                round,
                time,
                fighting_tomatoes_aggregate_rating,
                fighting_tomatoes_number_ratings
        );
    }
}

