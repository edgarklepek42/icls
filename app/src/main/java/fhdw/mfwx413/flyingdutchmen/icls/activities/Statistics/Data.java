package fhdw.mfwx413.flyingdutchmen.icls.activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fhdw.mfwx413.flyingdutchmen.icls.data.Challenge;
import fhdw.mfwx413.flyingdutchmen.icls.data.ChallengeCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.ChallengeDatabase;
import fhdw.mfwx413.flyingdutchmen.icls.data.Constants;
import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.IndexCard;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserProgress;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserProgressCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserProgressDatabase;

/**
 * Responsibility: Edgar Klepek
 */
public class Data {
    // Static variables for the bundle
    //private static final String KEY_DUE_CHALLENGES_OF_USER_IN_FILE = "K1";
    private static final String KEY_CHOSEN_USER = "K2";
    private static final String KEY_CHOSEN_FILE = "K3";

    private Activity mActivity;
    private User mChosenUser;
    private IndexCard mChosenFile;
    private int mNumberAllChallenges;
    private int mNumberDueChallenges;
    private int mNumberOfClass1;
    private int mNumberOfClass2;
    private int mNumberOfClass3;
    private int mNumberOfClass4;
    private int mNumberOfClass5;
    private int mNumberOfClass6;
    private ChallengeCollection mAllChallenges;

    public Data(Activity activity, Bundle bundle) {
        mActivity = activity;
        Intent intent;

        if(bundle == null) {
            // First start of the activity
            intent = mActivity.getIntent();
            mChosenUser = (User) intent.getSerializableExtra(Constants.KEY_PARAM_CHOSEN_USER);
            Log.d("Statistics::Data::User", "" + mChosenUser.getmName());
            mChosenFile = (IndexCard) intent.getSerializableExtra(Constants.KEY_PARAM_CHOSEN_FILE);
            Log.d("Statistics::Data::File", "" + mChosenFile.getmName());
        }
        else{
            // Restore Data if bundle is filled
            restoreDataFromBundle(bundle);
        }
    }

    // Save data in bundle if activity stops
    public void saveDataFromBundle(Bundle bundle) {
        bundle.putSerializable(KEY_CHOSEN_USER, mChosenUser);
        bundle.putSerializable(KEY_CHOSEN_FILE, mChosenFile);
    }

    // Restore data from given bundle
    public void restoreDataFromBundle(Bundle bundle) {
        mChosenUser = (User) bundle.getSerializable(KEY_CHOSEN_USER);
        mChosenFile = (IndexCard) bundle.getSerializable(KEY_CHOSEN_FILE);
    }

    public Activity getActivity() {
        return mActivity;
    }

    public User getmChosenUser() {
        return mChosenUser;

    }

    public IndexCard getmChosenFile() {
        return mChosenFile;
    }

    public void setmChosenFile(IndexCard mChosenFile) {
        this.mChosenFile = mChosenFile;
    }

    // Count the number of all existing challenges of the current file
    public int getmNumberAllChallenges() {
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                mNumberAllChallenges++;
            }
        }
        return mNumberAllChallenges;
    }

    // Get dueChallenges from Max
    public int getmNumberDueChallenges() {
        /*
        // TODO: Berechnung der Anzahl der fälligen Challenges der aktuellen Kartei und des aktuellen Users (Datumvergleich?)
        // Experiment --> zu kompliziert und redundant
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
        // DEMODATEN
        Date challengeTimestamp = new Date("2016.01.25.18.00.00");

        int numberDueChallenges = 0;
        UserProgressCollection mAllUserProgresses = UserProgressDatabase.getAllUserProgresses(mActivity);
        for(int i = 0; i < mAllUserProgresses.getSize(); i++) {
            UserProgress mUserProgress = mAllUserProgresses.getUserProgress(i);

            if(mUserProgress.getmChallengeID() ==  && mUserProgress.getmUserName() == mChosenUser.getmName()) {
                int compareResult = currentDate.compareTo(challengeTimestamp);
                if(compareResult >= mChosenUser.getmPeriodClass1() && all.get) {
                    numberDueChallenges++;
                }

                int compareResult = currentDate.compareTo(challengeTimestamp);
                if(compareResult >= mChosenUser.getmPeriodClass2() && all.get) {
                    numberDueChallenges++;
                }
            }

        }*/
        //int numberDueChallenges = getmNumberOfClass1() + getmNumberOfClass2() + getmNumberOfClass3() + getmNumberOfClass4() + getmNumberOfClass5() + getmNumberOfClass6();
        int numberDueChallenges = 0;
        return numberDueChallenges;
    }

    // Get all challenges with class 1 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass1() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassOne = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 1) {
                    countClassOne++;
                }
            }
        }
        return countClassOne;
    }

    // Get all challenges with class 2 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass2() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassTwo = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 2) {
                    countClassTwo++;
                }
            }
        }
        return countClassTwo;
    }

    // Get all challenges with class 3 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass3() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassThree = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 3) {
                    countClassThree++;
                }
            }
        }
        return countClassThree;
    }

    // Get all challenges with class 4 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass4() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassFour = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 4) {
                    countClassFour++;
                }
            }
        }
        return countClassFour;
    }

    // Get all challenges with class 5 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass5() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassFive = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 5) {
                    countClassFive++;
                }
            }
        }
        return countClassFive;
    }

    // Get all challenges with class 6 from progress.csv
    // First filter the index and then the class
    public int getmNumberOfClass6() {
        // Get all challenges
        mAllChallenges = ChallengeDatabase.getAllChallenges(mActivity);
        ChallengeCollection challengesFromIndex = new ChallengeCollection();

        // Gehe alle Challenges durch und gebe eine Collection aller des Index
        for(int i = 0; i < mAllChallenges.getSize(); i++) {
            Challenge mChallenge = mAllChallenges.getChallenge(i);
            if(mChallenge.getmIndexCard().getmID() == mChosenFile.getmID()) {
                challengesFromIndex.addChallenge(mAllChallenges.getChallenge(i));
            }
        }

        UserProgressCollection allProgressData = UserProgressDatabase.getUserProgresses(mActivity, mChosenUser.getmName());
        int countClassSix = 0;

        // Go through all user progresses and count the matching ones
        for(int j = 0; j < allProgressData.getSize(); j++) {
            UserProgress progress = allProgressData.getUserProgress(j);
            for(int k = 0; k < challengesFromIndex.getSize(); k++) {
                if (progress.getmChallengeID() == challengesFromIndex.getChallenge(k).getmID() && progress.getmPeriodClass() == 6) {
                    countClassSix++;
                }
            }
        }
        return countClassSix;
    }


}