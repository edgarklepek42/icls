package fhdw.mfwx413.flyingdutchmen.icls.activities.StartMenu;

import android.app.Activity;
import android.os.Bundle;

import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserDatabase;
import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Created by Edgar on 17.02.2016
 * Responsibility: Max Schumacher
 * Updated by Max on 20.02.2016
 * Updated by Max on 21.02.2016
 * Updated by Max on 23.02.2016
 * Updated by Max on 02.03.2016
 */

// Data initializes all the data that is relevant in the current activity
public class Data {

    private Activity mActivity;
    private User mCurrentUser;
    private UserCollection mAllUsers;

    public Data(Activity activity, Bundle savedInstanceState) {
        mActivity = activity;
        // collects all the existing user from UserDatabase
        mAllUsers = UserDatabase.getAllUser(mActivity);

        // If there is no existing user, go directly to the ActivityAddNewUser
        if(mAllUsers.getSize()==0) {
            Navigation.startActivityAddNewUser(getActivity());
        }
    }

    public UserCollection getAllUsers() {
        return mAllUsers;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public Activity getActivity() {
        return mActivity;
    }

    // sets the current User when a selection in the spinner was made
    public void setCurrentUser(User mCurrentUser) {
        this.mCurrentUser = mCurrentUser;
    }
}
