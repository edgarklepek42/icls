package fhdw.mfwx413.flyingdutchmen.icls.activities.AddNewUser;

import android.app.Activity;

import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserDatabase;

/**
 * Responsibility: Luisa Leifer
 */
public class Data {

    public Activity getActivity() {
        return mActivity;
    }

    private Activity mActivity;
    private User mGivenUser;
    private UserCollection mAllUsers;

    public Data(Activity activity) {
        mActivity = activity;
        mAllUsers = UserDatabase.getUser(mActivity);
        //mUserCollection = UserDatabase.getAllUsers();
        //mGivenUser = DEFAULT_GIVEN_USER;
    }

    public User getmGivenUser() {
        return mGivenUser;
    }

    /*public UserCollection getmUserCollection() {
        return mUserCollection;
    }*/

    public UserCollection getmAllUsers() {
        return mAllUsers;
    }
}
