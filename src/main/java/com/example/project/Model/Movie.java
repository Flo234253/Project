package com.example.project.Model;

public class Movie {
    private String aTitle;
    private String aGenre;
    private String aReleaseDate;
    private String aDuration;
    private String aActors;
    private String aDirector;
    private String aDescription;

    // Full constructor
    public Movie(String pTitle, String pGenre, String pReleaseDate, String pDuration, String pActors, String pDirector, String pDescription) {
        this.aTitle = pTitle;
        this.aGenre = pGenre;
        this.aReleaseDate = pReleaseDate;
        this.aDuration = pDuration;
        this.aActors = pActors;
        this.aDirector = pDirector;
        this.aDescription = pDescription;
    }

    // Getters
    public String getTitle() { return aTitle; }
    public String getGenre() { return aGenre; }
    public String getReleaseDate() { return aReleaseDate; }
    public String getDuration() { return aDuration; }
    public String getActors() { return aActors; }
    public String getDirector() { return aDirector; }
    public String getDescription() { return aDescription; }
}
