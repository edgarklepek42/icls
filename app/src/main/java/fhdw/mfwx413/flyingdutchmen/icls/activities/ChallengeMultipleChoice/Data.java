package fhdw.mfwx413.flyingdutchmen.icls.activities.ChallengeMultipleChoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fhdw.mfwx413.flyingdutchmen.icls.data.ChallengeCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.Constants;
import fhdw.mfwx413.flyingdutchmen.icls.data.IndexCard;
import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserProgressCollection;

/**
 * Responsibility: Daniel zur Linden
 */
public class Data {
    private static final int DEFAULT_CURRENT_CHALLENGE_ID = 0;


    private Activity mActivity;
    private ChallengeCollection mDueChallengesOfUserInFile;
    private int mCurrentChallengeId;
    private User mChosenUser;
    private IndexCard mChosenFile;
    private UserProgressCollection mUserProgresses;

    public Data(Activity activity, Bundle bundle) {
        mActivity = activity;
        Intent intent;

        if (bundle == null) {
            //if bundle isn't filled, the data will be initialized by the extras of the intent
            intent = mActivity.getIntent();
            mCurrentChallengeId = intent.getIntExtra(Constants.KEY_PARAM_CURRENT_CHALLENGE_ID, DEFAULT_CURRENT_CHALLENGE_ID);
            mDueChallengesOfUserInFile = (ChallengeCollection) intent.getSerializableExtra(Constants.KEY_PARAM_DUE_CHALLENGES_FOR_USER_IN_INDEX_CARD);
            mChosenUser = (User) intent.getSerializableExtra(Constants.KEY_PARAM_CHOSEN_USER);
            mChosenFile = (IndexCard) intent.getSerializableExtra(Constants.KEY_PARAM_CHOSEN_INDEX_CARD);
            mUserProgresses = (UserProgressCollection) intent.getSerializableExtra(Constants.KEY_PARAM_USER_PROGRESS_CURRENT_USER);
        } else {
            //restore Data if bundle is filled
            restoreDataFromBundle(bundle);
        }

    }

    //save data in bundle if activity stops
    public void saveDataFromBundle(Bundle bundle) {
        bundle.putInt(Constants.BUNDLE_KEY_CURRENT_CHALLENGE_ID, mCurrentChallengeId);
        bundle.putSerializable(Constants.BUNDLE_KEY_DUE_CHALLENGES_FOR_USER_IN_INDEX_CARD, mDueChallengesOfUserInFile);
        bundle.putSerializable(Constants.BUNDLE_KEY_CHOSEN_USER, mChosenUser);
        bundle.putSerializable(Constants.BUNDLE_KEY_CHOSEN_INDEX_CARD, mChosenFile);
    }

    //restore data from given bundle
    private void restoreDataFromBundle(Bundle bundle) {
        mCurrentChallengeId = bundle.getInt(Constants.BUNDLE_KEY_CURRENT_CHALLENGE_ID);
        mDueChallengesOfUserInFile = (ChallengeCollection) bundle.getSerializable(Constants.BUNDLE_KEY_DUE_CHALLENGES_FOR_USER_IN_INDEX_CARD);
        mChosenUser = (User) bundle.getSerializable(Constants.BUNDLE_KEY_CHOSEN_USER);
        mChosenFile = (IndexCard) bundle.getSerializable(Constants.BUNDLE_KEY_CHOSEN_INDEX_CARD);
    }

    public int getmCurrentChallengeId() {
        return mCurrentChallengeId;
    }

    public ChallengeCollection getmDueChallengesOfUserInFile() {
        return mDueChallengesOfUserInFile;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public User getmChosenUser() {
        return mChosenUser;
    }

    public UserProgressCollection getmUserProgresses() {
        return mUserProgresses;
    }

    public IndexCard getmChosenFile() {
        return mChosenFile;
    }
}


