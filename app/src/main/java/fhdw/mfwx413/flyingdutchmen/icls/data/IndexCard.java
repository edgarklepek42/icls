package fhdw.mfwx413.flyingdutchmen.icls.data;

import java.io.Serializable;

/**
 * Responsibility: Jonas Krabs
 */
public class IndexCard implements Serializable{

    private int mID;
    private String mName;

    public IndexCard(int mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }
}
