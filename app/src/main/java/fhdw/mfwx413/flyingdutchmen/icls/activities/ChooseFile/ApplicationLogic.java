package fhdw.mfwx413.flyingdutchmen.icls.activities.ChooseFile;

import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;
import fhdw.mfwx413.flyingdutchmen.icls.data.csvImport;
import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;
import android.content.Context;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 13.02.2016
 * Updated by Max on 20.12.2016
 */

public class ApplicationLogic {

    private Data mData;
    private Gui mGui;
    private Context context;

    public static List<String[]> files = new ArrayList<>();

    public ApplicationLogic(Data data, Gui gui, Context context) {
        mData = data;
        mGui = gui;
        this.context = context;
        initialUpdateDataToGui();
        fillSpinner();
    }

    private void initialUpdateDataToGui() {

    }

    // Noch nicht fertig
    public void onButtonStatisticsClicked() {
        Navigation.startActivityStatistics(mData.getActivity());
    }

    public void onButtonLogoutClicked() {
        // Abmelden-Fragment
        Navigation.startActivityStatistics(mData.getActivity());
    }

    public void onButtonSettingsClicked() {
        Navigation.startActivitySettingMenu(mData.getActivity());
    }

    public void onButtonStartLearningClicked() {
        // Start Lernmodus mit fälligen Fragen oder Ende-Screen
        Navigation.startActivityStatistics(mData.getActivity());
    }

    private void fillSpinner() {
        files = csvImport.importIndexCsv(context);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, (List<String>) files);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mGui.getFiles().setAdapter(adapter);
    }

}
