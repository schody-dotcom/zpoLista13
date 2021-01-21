package com.example.edu.sqliteprzyklad;

public class COVIDData {

private String country;
private int cases;
private int active;
    private int casesPerOneMillion;
    private int testsPerOneMillion;


    public COVIDData(String country, int cases, int active, int casesPerOneMillion, int testsPerOneMillion) {
        this.country = country;
        this.cases = cases;
        this.active = active;
        this.casesPerOneMillion = casesPerOneMillion;
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public int getTestsPerOneMillion() {
        return testsPerOneMillion;
    }
}
