package fhdw.mfwx413.flyingdutchmen.icls.activities.SettingsMenu;


import android.app.Activity;

import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserDatabase;

/**
 * Responsibility: Daniel zur Linden
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
        mAllUsers = UserDatabase.getAllUser(mActivity);
    }

    public User getmGivenUser() {
        return mGivenUser;
    }

    public UserCollection getmUserCollection() {
        return mAllUsers;
    }

    public UserCollection getmAllUsers() {
        return mAllUsers;
    }

    public User getCurrentUser() {
        return mGivenUser;
    }

    public void setmCurrentUser(User mCurrentUser) {
        this.mGivenUser = mCurrentUser;
    }
}
