package com.todo.fursa.ui.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.todo.fursa.R
import com.todo.fursa.TodoApplication
import com.todo.fursa.alarm.AlarmReceiver
import com.todo.fursa.room.model.Todo
import com.todo.fursa.ui.viewmodel.MainViewModel
import com.todo.fursa.util.toTimestamp
import java.util.*
import javax.inject.Inject

class AddTodoBottomSheet: BottomSheetDialogFragment() {
    private lateinit var btnAdd: MaterialButton
    private lateinit var btnDismiss: MaterialButton
    private lateinit var rootView: View
    private lateinit var tilTitle: TextInputEditText
    private lateinit var tilText: TextInputEditText
    private lateinit var tilTime: TextInputEditText
    private lateinit var tilDate: TextInputEditText

    private lateinit var checkBoxAsap: CheckBox

    private var priorityValue: Int = 0 //0 - low  1 - asap

    private lateinit var viewModel: MainViewModel

    private lateinit var intentAlarm: Intent
    private lateinit var pendingIntent: PendingIntent
    private lateinit var date: Date

    @Inject
    lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var calendar: Calendar

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        TodoApplication.component.inject(this)
        viewModel = ViewModelProviders.of(this@AddTodoBottomSheet).get(MainViewModel::class.java)

        rootView = layoutInflater.inflate(R.layout.bottom_sheet_add_todo, container, false)

        btnAdd = rootView.findViewById(R.id.btnAdd)
        btnDismiss = rootView.findViewById(R.id.btnDismiss)

        tilTitle = rootView.findViewById(R.id.tilTitle)
        tilText = rootView.findViewById(R.id.tilText)

        tilTime = rootView.findViewById(R.id.tilAlarmTime)
        tilDate = rootView.findViewById(R.id.tilAlarmDate)

        checkBoxAsap = rootView.findViewById(R.id.chbASAP)

        btnAdd.setOnClickListener {
            if(tilTitle.text!!.isEmpty() && tilText.text!!.isEmpty()) {
                Toast.makeText(context, "Вы пытаетесь добавить пустую заметку!", Toast.LENGTH_LONG).show()
            } else {

                priorityValue = when {
                    checkBoxAsap.isChecked -> 1
                    else -> 0
                }

                Log.d(LOG_TAG, "Priority: $priorityValue")


            date = Date(toTimestamp(tilTime.text.toString(), tilDate.text.toString()))
            calendar.set(Calendar.HOUR_OF_DAY, date.hours)
            calendar.set(Calendar.MINUTE, date.minutes)

            intentAlarm = Intent(this.activity, AlarmReceiver::class.java)

            intentAlarm.putExtra(TODO_TITLE, tilTitle.text.toString())
            intentAlarm.putExtra(TODO_TEXT, tilText.text.toString())

            viewModel.insert(Todo(tilTitle.text.toString(), tilText.text.toString(), priorityValue, toTimestamp(tilTime.text.toString(), tilDate.text.toString())))

            pendingIntent = PendingIntent.getBroadcast(this.activity, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)


            Toast.makeText(context, "${tilTitle.text.toString()} - добавлено!", Toast.LENGTH_SHORT).show()
            dismiss()

            }
        }

        btnDismiss.setOnClickListener { dismiss() }

        return rootView
    }

    companion object {
        fun newInstance(): BottomSheetDialogFragment {
            val dialog = AddTodoBottomSheet()
            val args = Bundle()
            dialog.arguments = args
            return dialog
        }

        const val LOG_TAG = "Todo/AddBottomSheet"

        const val TODO_TITLE = "todo_title"
        const val TODO_TEXT = "todo_text"
    }

}