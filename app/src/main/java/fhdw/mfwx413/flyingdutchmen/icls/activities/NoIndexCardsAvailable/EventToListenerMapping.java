package fhdw.mfwx413.flyingdutchmen.icls.activities.NoIndexCardsAvailable;

import android.view.View;

import fhdw.mfwx413.flyingdutchmen.icls.R;

/**
 * Created by edgar on 17.02.2016.
 * Updated by Max on 01.03.2016
 */
public class EventToListenerMapping implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
        gui.getButtonOk3().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk3:
                mApplicationLogic.onButtonOkClicked();
                break;
        }
    }
}
