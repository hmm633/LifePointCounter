package cs371m.lifepointcounter;

/**
 * Created by Eddy on 5/4/2016.
 */

//package info.androidhive.recyclerview;

    // We currently aren't using "time" due to time constraints (ha)
public class Entry {
    private String name, lifeChange, lifeNow, time;
//    private Integer lifeChange, lifeNow, time;

    public Entry(){
    }

    public Entry(String name, String lifeChange, String lifeNow, String time){
        this.name = name;
        this.lifeChange = lifeChange;
        this.lifeNow = lifeNow;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public String getLifeChange(){
        return lifeChange;
    }

    public String getLifeNow(){
        return lifeNow;
    }

    public String getTime(){
        return time;
    }
}