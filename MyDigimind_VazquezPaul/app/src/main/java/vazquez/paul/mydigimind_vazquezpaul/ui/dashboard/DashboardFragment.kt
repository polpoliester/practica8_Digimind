package vazquez.paul.mydigimind_vazquezpaul.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vazquez.paul.mydigimind_vazquezpaul.R
import vazquez.paul.mydigimind_vazquezpaul.ui.Task
import vazquez.paul.mydigimind_vazquezpaul.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()

        }
        val btn_save = root.findViewById<Button>(R.id.btn_save)
        val et_titulo = root.findViewById<EditText>(R.id.et_task)
        val checkMonday = root.findViewById<CheckBox>(R.id.checkMonday)
        val checkTuesday = root.findViewById<CheckBox>(R.id.checkTuesday)
        val checkWednesday = root.findViewById<CheckBox>(R.id.checkWednesday)
        val checkThursday = root.findViewById<CheckBox>(R.id.checkThursday)
        val checkFriday = root.findViewById<CheckBox>(R.id.checkFriday)
        val checkSaturday = root.findViewById<CheckBox>(R.id.checkSaturday)
        val checkSunday = root.findViewById<CheckBox>(R.id.checkSunday)

        btn_save.setOnClickListener {
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            if (checkMonday.isChecked) {
                days.add("Monday")
            }
            if (checkTuesday.isChecked) {
                days.add("Tuesday")
            }
            if (checkWednesday.isChecked) {
                days.add("Wednesday")
            }
            if (checkThursday.isChecked) {
                days.add("Thursday")
            }
            if (checkFriday.isChecked) {
                days.add("Friday")
            }
            if (checkSaturday.isChecked) {
                days.add("Saturday")
            }
            if (checkSunday.isChecked) {
                days.add("Sunday")
            }

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added!", Toast.LENGTH_SHORT).show()
        }
        return root
    }
}