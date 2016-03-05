package fhdw.mfwx413.flyingdutchmen.icls.activities.DuplicateFiles;

import android.content.Context;

import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Created by Edgar on 17.02.2016
 * Responsibility: Max Schumacher
 * Updated by Max on 03.03.2016
 */
public class ApplicationLogic {

    private Data mData;
    private Gui mGui;
    private Context context;

    public ApplicationLogic(Data data, Gui gui, Context context) {
        mData = data;
        mGui = gui;
        this.context = context;
        initialUpdateDataToGui();
    }

    private void initialUpdateDataToGui() {

    }

    public void onStandardBackButtonClicked() {

    }

    public void onButtonOkClicked() {
        Navigation.startActivityStartMenu(mData.getActivity());
    }

}
