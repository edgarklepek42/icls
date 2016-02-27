package fhdw.mfwx413.flyingdutchmen.icls.activities.Statistics;

import android.app.Activity;
import android.os.Bundle;

import fhdw.mfwx413.flyingdutchmen.icls.R;
import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Responsibility: Edgar Klepek
 */
public class Init extends Activity {

    public Data mData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        initGui();
        initApplicationLogic();
        initEventToListenerMapping();
    }

    private void initApplicationLogic(){
        mApplicationLogic = new ApplicationLogic(mData, mGui, this);
    }

    private void initEventToListenerMapping() {
        new EventToListenerMapping(mGui, mApplicationLogic);
    }

    private void initGui() {
        mGui = new Gui(this);
    }

    private void initData(Bundle savedInstanceState){
        mData = new Data(this, savedInstanceState);
    }

    // Save data if activity stops
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mData.saveDataFromBundle(outState);
        super.onSaveInstanceState(outState);
    }

    // Back to layout_choose_file (back button)
    @Override
    public void onBackPressed() {
        mApplicationLogic.onButtonBackToChooseFileClicked();
    }
}