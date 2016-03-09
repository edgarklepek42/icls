package fhdw.mfwx413.flyingdutchmen.icls.activities.AddNewUser;

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
        gui.getmButtonAbortNewUser().setOnClickListener(this);
        gui.getmButtonSaveNewUser().setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            // calling the methods in ApplicationLogic depending on which button was clicked
            case R.id.buttonAbortNewUser:
                mApplicationLogic.onButtonAbortNewUserClicked();
                break;
            case R.id.buttonSaveNewUser:
                mApplicationLogic.onButtonSaveNewUserClicked();
                break;
        }
    }
}