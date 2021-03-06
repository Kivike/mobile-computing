package fi.oulu.reminder2020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdapter(context: Context, private val list: List<Reminder>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = inflater.inflate(R.layout.list_view_item, parent, false)
        row.itemMessage.text = list[position].message

        val time = list[position].time

        if (time != null) {
            val sdf = SimpleDateFormat("HH:mm:ss dd.MM.yyyy")
            sdf.timeZone = TimeZone.getDefault()

            val formattedTime = sdf.format(time)
            row.itemTrigger.text = formattedTime
        } else {
            row.itemTrigger.text = "location"
        }

        row.itemTrigger.text = list[position].time.toString()

        return row
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}