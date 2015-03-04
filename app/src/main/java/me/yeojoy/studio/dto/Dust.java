package me.yeojoy.studio.dto;

/**
 * Created by yeojoy on 15. 1. 2..
 */
public class Dust {
    public Dust() {}

    public Dust(String localtity, String pm10, String maxIndex) {
        this.localtity = localtity;
        this.pm10 = pm10;
        this.maxIndex = maxIndex;
    }

    @Override
    public String toString() {
        return "Dust{" +
                "localtity='" + localtity + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", maxIndex='" + maxIndex + '\'' +
                ", isChecked='" + isChecked + '\'' +
                '}';
    }

    public String getLocaltity() {
        return localtity;
    }

    public void setLocaltity(String localtity) {
        this.localtity = localtity;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(String maxIndex) {
        this.maxIndex = maxIndex;
    }

    private String localtity;
    private String pm10;
    private String maxIndex;

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }
    
    public void toggleChecked() {
        isChecked = !isChecked;
    }

    private boolean isChecked;
}
