package fhdw.mfwx413.flyingdutchmen.icls.activities.ChooseFile;

import android.view.View;
import android.widget.AdapterView;

import fhdw.mfwx413.flyingdutchmen.icls.R;

/**
 * Created by edgar on 13.02.2016
 * Updated by Max on 20.12.2016
 */

public class EventToListenerMapping implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
        gui.getButtonLogout().setOnClickListener(this);
        gui.getButtonStatistics().setOnClickListener(this);
        gui.getButtonSettings().setOnClickListener(this);
        gui.getButtonStartLearning().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonLogout:
                mApplicationLogic.onButtonLogoutClicked();
                break;
            case R.id.buttonStatistics:
                mApplicationLogic.onButtonStatisticsClicked();
                break;
            case R.id.buttonSettings:
                mApplicationLogic.onButtonSettingsClicked();
                break;
            case R.id.buttonStartLearning:
                mApplicationLogic.onButtonStartLearningClicked();
                break;
            //TODO Max: ZurückButton
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mApplicationLogic.onUserSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Spinner is always filled with init of activity, therefore method doesn't need to be filled
    }
}
