package com.example.tp1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var Pseudo: EditText? = null
    private var BtnOK: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp.edit()

        //sp = getSharedPreferences("sp",Context.MODE_PRIVATE)
        Pseudo = findViewById(R.id.Pseudo)
        BtnOK = findViewById(R.id.ButtonOk)

        BtnOK!!.setOnClickListener(this)



        var l=sp.getString("login","null")
        Pseudo?.setText(l.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_settings-> {
                val iGP = Intent(this, SettingsActivity::class.java)
                startActivity(iGP)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id)
        {
            R.id.ButtonOk ->
            {
                Log.i("PMR","clickok")
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                //Garder dans shared preferences
                editor.putString("login", Pseudo?.text.toString())
                editor.commit()

                var l= sp.getString("login","gf")
                Log.i("PMR",l.toString())


                //Changer Activite
                val versSecondAct: Intent = Intent(this@MainActivity, ChoixListActivity::class.java)
                //Envoyer donnes
                versSecondAct.putExtra("pseudo",Pseudo?.text.toString())
                startActivity(versSecondAct)

            }
        }
    }

}