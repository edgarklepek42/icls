package fhdw.mfwx413.flyingdutchmen.icls.activities.AddNewUser;

import android.app.Activity;
import android.os.Bundle;

/**
 * Responsibility: Luisa Leifer
 */
public class Init extends Activity {

    public Data mData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initData();
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

    private void initData(){
        mData = new Data(this);
    }

}
