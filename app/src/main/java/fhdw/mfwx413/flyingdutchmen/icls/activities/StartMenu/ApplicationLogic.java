package fhdw.mfwx413.flyingdutchmen.icls.activities.StartMenu;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Created by edgar on 17.02.2016
 * Responsibility: Max
 * Updated by Max on 20.02.2016
 * Updated by Edgar on 21.02.2016
 * Updated by Jonas on 26.02.2016
 */
public class ApplicationLogic{

    private Data mData;
    private Gui mGui;
    private int count;
    private Context context;
    private ArrayList<String> userNames = new ArrayList<>();
    private String mselectedName;

    public ApplicationLogic(Data data, Gui gui, Context context) {
        mData = data;
        mGui = gui;
        this.context = context;
        initialUpdateDataToGui();
        fillSpinner();
    }

    private void initialUpdateDataToGui() {
    }

    public void onButtonAddUserClicked() {
        Navigation.startActivityAddNewUser(mData.getActivity());
    }

    public void onButtonConfirmUserClicked() {
        mData.setCurrentUser(mData.getmAllUsers().getUser(mselectedName));
        Navigation.startActivityChooseFile(mData.getActivity(), mData.getCurrentUser());
    }

    public void onButtonEditUserClicked() {
        mData.setCurrentUser(mData.getmAllUsers().getUser(mselectedName));
        Navigation.startActivityEditUser(mData.getActivity(), mData.getCurrentUser());
    }

    // Added by Edgar Klepek
    // Fill the spinner with data given by users.csv and show it
    private void fillSpinner() {

        for(int i = 0; i < mData.getmAllUsers().getSize(); i++) {
            userNames.add(mData.getmAllUsers().get(i).getmName());
        }

        // ZUM TESTEN; KANN SPÄTER WIEDER WEG
        count = userNames.size();
        Log.d("users.size: ", "" + count);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, userNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mGui.getChooseUser().setAdapter(adapter);
    }

    //Set selected User from Spinner
    public void onUserSelected(int position){
        System.out.println(userNames.get(position));
        mselectedName = userNames.get(position);
    }

}