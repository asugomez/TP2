package com.example.tp1.ui.main

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
import com.example.tp1.R
import com.example.tp1.data.DataProvider
import com.example.tp1.data.DataProvider.connexion
import com.example.tp1.data.model.Item
import com.example.tp1.data.model.LoginResponse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



//@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var Pseudo: EditText? = null
    private var Mdp: EditText? = null
    private var BtnOK: Button? = null

    private val activityScope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.Main
                + CoroutineExceptionHandler { _, throwable ->
            Log.e("MainActivity", "CoroutineExceptionHandler : ${throwable.message}")
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp.edit()

        //sp = getSharedPreferences("sp",Context.MODE_PRIVATE)
        Pseudo = findViewById(R.id.Pseudo)
        Mdp = findViewById(R.id.editTextTextPassword)
        BtnOK = findViewById(R.id.ButtonOk)

        BtnOK!!.setOnClickListener(this)


        val l=sp.getString("login","null")
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
            R.id.action_settings -> {
                val iGP = Intent(this, SettingsActivity::class.java)
                startActivity(iGP)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ButtonOk -> {

                Log.i("PMR", "clickok")
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()

                val l = sp.getString("login", "gf")
                Log.i("PMR", l.toString())

                activityScope.launch {
                    try{
                        val hash: String = connexion(Pseudo.toString(), Mdp.toString())
                        if (!hash.isEmpty()) {
                            //Garder dans shared preferences
                            editor.putString("login", Pseudo?.text.toString())
                            editor.commit()

                            //Changer Activite
                            val versSecondAct: Intent =
                                Intent(this@MainActivity, ChoixListActivity::class.java)
                            //Envoyer donnes
                            //versSecondAct.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            versSecondAct.putExtra("pseudo", Pseudo?.text.toString())
                            versSecondAct.putExtra("hash", hash)
                            startActivity(versSecondAct)
                        } else {
                            Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception){
                        Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }
    }


}