package fhdw.mfwx413.flyingdutchmen.icls.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 22.02.2016.
 * Responsibility: Daniel zur Linden
 */
public class UserProgress implements Serializable{
    private String mUserName;
    private int mChallengeID;
    private int mPeriodClass;
    private String mTimeStampAnswered;

    public UserProgress(String UserName, int ChallengeID, int Zeitklasse, String TimeStampBeantwortung) {
        this.mUserName = UserName;
        this.mChallengeID = ChallengeID;
        this.mPeriodClass = Zeitklasse;
        this.mTimeStampAnswered = TimeStampBeantwortung;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public int getmChallengeID() {
        return mChallengeID;
    }

    public void setmChallengeID(int mChallengeID) {
        this.mChallengeID = mChallengeID;
    }

    public int getmPeriodClass() {
        return mPeriodClass;
    }

    public void setmPeriodClass(int mPeriodClass) {
        this.mPeriodClass = mPeriodClass;
    }

    public String getmTimeStampAnswered() {
        return mTimeStampAnswered;
    }

    public void setmTimeStampAnswered(String mTimeStampAnswered) {
        this.mTimeStampAnswered = mTimeStampAnswered;
    }

    public void setDefaultTimeStamp(){
        setmTimeStampAnswered(Constants.DEFAULT_TIMESTAMP);
    }

    public void setCurrentTimeStamp(){
        setmTimeStampAnswered(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    }

}


