package fi.oulu.reminder2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_time.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.util.*

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        time_create.setOnClickListener {
            val calendar = GregorianCalendar(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
                timePicker.currentHour,
                timePicker.currentMinute
            )
            val reminder = Reminder(
                uid = null,
                time = calendar.timeInMillis,
                location = null,
                message = et_message.text.toString()
            )

            if (validateReminder(reminder)) {
                doAsync {
                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "reminders"
                    ).build()

                    db.reminderDao().insert(reminder)
                    db.close()
                    finish()
                }
            } else {
                toast("Invalid reminder")
            }
        }
    }

    private fun validateReminder(reminder: Reminder): Boolean {
        return (reminder.time as Long > System.currentTimeMillis() &&
                reminder.message != "")
    }

    private fun setAlarm(time: Long, message: String) {

    }
}
