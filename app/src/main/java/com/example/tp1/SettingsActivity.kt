package com.example.tp1

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.widget.Toast
import androidx.annotation.Nullable

@Suppress("DEPRECATION")
class SettingsActivity() : PreferenceActivity(){

    public override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.root_preferences)

    }

}
