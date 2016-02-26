package fhdw.mfwx413.flyingdutchmen.icls.activities.StartMenu;

import android.view.View;
import android.widget.AdapterView;

import fhdw.mfwx413.flyingdutchmen.icls.R;

/**
 * Created by edgar on 17.02.2016
 * Responsibility: Max Schumacher
 * Updated by Max Schumacher on 20.02.2016
 */
//Todo Jonas: saubere trennung von EventToListenerMapping und ApplicationLogic realisieren
public class EventToListenerMapping implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
        gui.getButtonAddUser().setOnClickListener(this);
        gui.getButtonConfirmUser().setOnClickListener(this);
        gui.getButtonEditUser().setOnClickListener(this);
        gui.getChooseUser().setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonAddUser:
                mApplicationLogic.onButtonAddUserClicked();
                break;
            case R.id.buttonConfirmUser:
                mApplicationLogic.onButtonConfirmUserClicked();
                break;
            case R.id.buttonEditUser:
                mApplicationLogic.onButtonEditUserClicked();
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mApplicationLogic.onUserSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Spinner is always filled init of activity, therefore method doesnt need to be filled
    }


}
