package fhdw.mfwx413.flyingdutchmen.icls.activities.StartMenu;

import android.view.View;
import android.widget.AdapterView;

import fhdw.mfwx413.flyingdutchmen.icls.R;
import fhdw.mfwx413.flyingdutchmen.icls.exceptions.IdNotFoundException;

/**
 * Created by Edgar on 17.02.2016
 * Responsibility: Max Schumacher
 * Updated by Max on 20.02.2016
 * Updated by Max on 21.02.2016
 * Updated by Max on 23.02.2016
 * Updated by Max on 02.03.2016
 */

// EventToListenerMapping connects the objects from Gui with the events of application logic
public class EventToListenerMapping implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ApplicationLogic mApplicationLogic;

    public EventToListenerMapping(Gui gui, ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
        gui.getButtonAddUser().setOnClickListener(this);
        gui.getButtonConfirmUser().setOnClickListener(this);
        /** Function not supported in this version of app
        gui.getButtonEditUser().setOnClickListener(this);
        */
        gui.getChooseUser().setOnItemSelectedListener(this);
    }

    // onClick defines what methods of application logic are called after a certain user-interaction
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonAddUser:
                mApplicationLogic.onButtonAddUserClicked();
                break;
            case R.id.buttonConfirmUser:
                try {
                    mApplicationLogic.onButtonConfirmUserClicked();
                } catch (IdNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            /** Function not supported in this version of app
            case R.id.buttonEditUser:
                try {
                    mApplicationLogic.onButtonEditUserClicked();
                } catch (IdNotFoundException e) {
                    e.printStackTrace();
                }
                break;
             */
        }
    }

    // Transfers the selected element in the spinner to the application logic
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mApplicationLogic.onUserSelected(position);
    }

    //Spinner is always filled with initialization of activity, therefore method doesn't need to be filled
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
