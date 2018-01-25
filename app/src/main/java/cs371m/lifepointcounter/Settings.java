package cs371m.lifepointcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

/**
 * Created on 4/2/2016.
 */
public class Settings extends PreferenceActivity {

    private static final String TAG = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //The following line is needed if we created a set preference file in the main java.file
        //without the line, a default is used
        //getPreferenceManager().setSharedPreferencesName(something);

        addPreferencesFromResource(R.xml.preferences);

        //The rest of the code is to update the summary whenever the user changes the settings

        //The following line will the be changed too
//        final SharedPreferences prefs = PreferenceManager.getSharedPreferences(something);
        //whenever(or if) we create our own preference file


        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final EditTextPreference messagePref = (EditTextPreference) findPreference("starting_life");
        String message = prefs.getString("starting_life",
                getResources().getString(R.string.starting_life_total));
        messagePref.setSummary((CharSequence) message);
        messagePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                messagePref.setSummary((CharSequence) newValue);

                // Since we are handling the pref, we must save it
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("starting_life", newValue.toString());
                Log.d(TAG, "Starting life is now: " + newValue.toString());
                ed.apply();
                return true;
            }
        });

        final EditTextPreference messagePref2 = (EditTextPreference) findPreference("tap_life_change");
        String message2 = prefs.getString("tap_life_change",
                getResources().getString(R.string.tap_life_change));
        messagePref2.setSummary((CharSequence) message2);
        messagePref2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                messagePref2.setSummary((CharSequence) newValue);

                // Since we are handling the pref, we must save it
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("tap_life_change", newValue.toString());
                Log.d(TAG, "Tap life change is now: " + newValue.toString());
                ed.apply();
                return true;
            }
        });

        final EditTextPreference messagePref3 = (EditTextPreference) findPreference("swipe_life_change");
        String message3 = prefs.getString("swipe_life_change",
                getResources().getString(R.string.swipe_life_change));
        messagePref3.setSummary((CharSequence) message3);
        messagePref3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                messagePref3.setSummary((CharSequence) newValue);

                // Since we are handling the pref, we must save it
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("swipe_life_change", newValue.toString());
                Log.d(TAG, "Swipe life change is now: " + newValue.toString());
                ed.apply();
                return true;
            }
        });

    }


}