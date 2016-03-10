package fhdw.mfwx413.flyingdutchmen.icls.activities.FinalEndOfChallenges;

import android.view.View;

import fhdw.mfwx413.flyingdutchmen.icls.R;

/**
 * Responsibility: Luisa Leifer
 */
public class EventToListenerMapping implements View.OnClickListener {
    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
        // equip the gui elements with listeners
        gui.getmButtonBackToChooseFile().setOnClickListener(this);
        gui.getmButtonStatistics().setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            // calling the methods in ApplicationLogic depending on which button was clicked
            case R.id.buttonBackToChooseFile:
                mApplicationLogic.onButtonBackToChooseFileClicked();
                break;
            case R.id.buttonStatistics:
                mApplicationLogic.onButtonStatisticsClicked();
                break;
        }
    }
}
