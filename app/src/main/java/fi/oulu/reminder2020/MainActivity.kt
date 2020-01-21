package fi.oulu.reminder2020

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_time.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }

        fab_map.setOnClickListener{
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }

        var fabOpened = false

        fab_add.setOnClickListener{
            if (!fabOpened) {
                fabOpened = true
                fab_map.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                fab_time.animate().translationY(-resources.getDimension(R.dimen.standard_116))
            } else {
                fabOpened = false
                fab_map.animate().translationY(0f)
                fab_time.animate().translationY(0f)
            }
        }
    }
}