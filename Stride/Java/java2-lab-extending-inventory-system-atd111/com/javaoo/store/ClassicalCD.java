package com.javaoo.store;

import java.util.Date;

public class ClassicalCD extends Item{

    //variables
    private String composer;
    private String[] performers = new String[5];
    private String recordingLocation;
    private Date releaseDate;
    private int performerCount = 0;

    public ClassicalCD(String title, double price, int quantity, String composer, String[] performers, String location 
    , Date releaseDate) {
        super(title, price, quantity);
        this.setComposer(composer);
        for (int i = 0; i < (performers.length - 1); i++){
            addPerformer(performers[i]);
        }
        this.setRecordingLocation(location);
        this.setReleaseDate(releaseDate);
    }

    //getters and setters
    public String getComposer() {
        return composer;
    }
    public void setComposer(String composer) {
        this.composer = composer;
    }
    public String getRecordingLocation() {
        return recordingLocation;
    }
    public void setRecordingLocation(String recordingLocation) {
        this.recordingLocation = recordingLocation;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void addPerformer(String performer) {
        // for(int i = 0; i < performers.length; i++) {
        //     if (!performers[i].isEmpty()) {
        //         performerCount++;
        //     }
        // }

        if (performerCount < performers.length) {
            performers[(performerCount - 1) + 1] = performer;
            performerCount++;
        }
        else {
            System.out.println("There are already 5 performers so no more can be added!");
        }
        
    }

    public void showPerformers() {
        for (int i = 1; i <= performerCount; i++) {
            int count = 1;
            System.out.println("Performer " + count + " 's name is: " + performers[i - 1]);
            count++;
        }
    }

    
    
}
