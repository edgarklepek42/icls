package fhdw.mfwx413.flyingdutchmen.icls.activities.AddNewUser;

import android.app.Activity;
import android.widget.Toast;

import fhdw.mfwx413.flyingdutchmen.icls.data.User;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserCollection;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserDatabase;
import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Responsibility: Luisa Leifer
 */
public class ApplicationLogic {

    private Data mData;
    private Gui mGui;
    private Activity mActivity;

    public ApplicationLogic(Data data, Gui gui, Activity activity) {
        mData = data;
        mGui = gui;
        mActivity = activity;
        initialUpdateGui();
    }

    private void initialUpdateGui() {
        /*User user;
        int currentUser;

        currentUser = mData.getmCurrentUser();
        user = mData.getmUserCollection().getUser(currentUser);*/
    }

    public void onButtonSaveNewUserClicked(){
        String givenUser;
        givenUser = mGui.getmNameOfUser().getText().toString();

        User newUser = new User();
        newUser.setCreateUser(givenUser);

        if (givenUser.isEmpty()){
            //Toast --> no input
            Toast.makeText(mData.getActivity(), "Bitte einen Namen eingeben!", Toast.LENGTH_LONG).show();
        }
        else {
            if (givenUser.matches("[a-zA-Z]++")) {
                //Toast accepted
                Toast.makeText(mData.getActivity(), "Username wurde akzeptiert!", Toast.LENGTH_LONG).show();

                //save new User and check if not exists
                UserCollection uc = mData.getmAllUsers();
                if(uc.doesUserExist(newUser) == false) {
                    mData.getmAllUsers().addUser(newUser);
                }
                else {
                    Toast.makeText(mData.getActivity(), "Username bereits vorhanden!", Toast.LENGTH_LONG).show();
                }

                // Export all users + new user to users.csv (create new csv file)
                // csvExport.exportUserToCsv();

                //Navigation to ChooseFile
                Navigation.startActivityChooseFile(mData.getActivity(), mData.getmGivenUser());
            } else {
                //Toast rejekted --> no special signs, no mutated vowels, no numbers
                Toast.makeText(mData.getActivity(), "Der Username darf keine Leerzeichen, Umlaute, Sonderzeichen und Ziffern enthalten!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onButtonAbortNewUserClicked(){
        Navigation.startActivityStartMenu(mData.getActivity());
    }
}
