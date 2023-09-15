package com.frankz.pills_reminder.treatments

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.frankz.pills_reminder.MainActivity
import com.frankz.pills_reminder.R
import com.frankz.pills_reminder.dtos.MedicamentDto
import com.frankz.pills_reminder.helpers.NotificationReceiver
import com.frankz.pills_reminder.models.User
import com.frankz.pills_reminder.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class NewTreatment : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var user: User? = null
    private val medicaments = arrayListOf<MedicamentDto>()
    private var alarmManager: AlarmManager? = null
    private var notificationCalendar: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_treatment)

        val isLogged = auth.currentUser != null
        if (!isLogged) {
            finish()
            goToActivity(MainActivity::class.java)
            return
        }
        notificationCalendar = Calendar.getInstance()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        user = intent.getParcelableExtra<User>("user")

        setInputDateListeners()
        setInputTimeListener()

        val btnAddMedicament = findViewById<Button>(R.id.btn_add_medicament)
        btnAddMedicament.setOnClickListener {
            addMedicament()
        }
        val btnSaveTreatment = findViewById<Button>(R.id.btn_save_treatment)
        btnSaveTreatment.setOnClickListener {
            saveTreatment()
        }

        navigationNavBar()
    }

    private fun setInputDateListeners() {
        val inputStartDate = findViewById<EditText>(R.id.input_initial_date)
        val inputEndDate = findViewById<EditText>(R.id.input_final_date)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        inputStartDate.setText(dateFormat.format(Calendar.getInstance().time))

        inputStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                inputStartDate.setText(date)
            }, year, month, dayOfMonth)

            datePickerDialog.show()
        }

        inputEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/${month + 1}/$year"
                inputEndDate.setText(date)
            }, year, month, dayOfMonth)

            datePickerDialog.show()
        }
    }



    private fun setInputTimeListener() {
        val inputTime = findViewById<EditText>(R.id.input_hour)

        inputTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, hour, minute ->
                val time = "$hour:$minute"
                inputTime.setText(time)
            }, hour, minute, true)

            timePickerDialog.show()
        }
    }
    private fun saveTreatment() {
        val userId = user!!.id
        val inputDisease = findViewById<TextView>(R.id.input_disease)
        val inputDescription = findViewById<TextView>(R.id.input_description)
        val inputInitialDate = findViewById<TextView>(R.id.input_initial_date)
        val inputFinalDate = findViewById<TextView>(R.id.input_final_date)

        val disease = inputDisease.text.toString()
        val description = inputDescription.text.toString()
        val initialDate = inputInitialDate.text.toString()
        val finalDate = inputFinalDate.text.toString()

        if (disease.isEmpty() || description.isEmpty() || initialDate.isEmpty() || finalDate.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        if (medicaments.isEmpty()) {
            Toast.makeText(this, "Por favor, agregue al menos un medicamento", Toast.LENGTH_LONG).show()
            return
        }

        if (userId!!.isEmpty() || userId == null) {
            return
        }

        val treatment = hashMapOf(
            "disease" to disease,
            "description" to description,
            "initialDate" to initialDate,
            "finalDate" to finalDate,
            "userId" to userId
        )

        db.collection("treatments")
            .add(treatment)
            .addOnSuccessListener {
                Toast.makeText(this, "Tratamiento agregado", Toast.LENGTH_LONG).show()

                val treatmentId = it.id
                for (medicament in medicaments) {
                    db.collection("treatments")
                        .document(treatmentId)
                        .collection("medicaments")
                        .add(medicament)
                        .addOnSuccessListener {
                            val intent = Intent(this, NotificationReceiver::class.java)
                            intent.putExtra("medicamentName", medicament.name)
                            intent.putExtra("medicamentId", it.id)
                            val time = medicament.hour.split(":")
                            val hour = time[0].toInt()
                            val minute = time[1].toInt()

                            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                            notificationCalendar!!.set(Calendar.HOUR_OF_DAY, hour)
                            notificationCalendar!!.set(Calendar.MINUTE, minute)


                            alarmManager!!.setExact(AlarmManager.RTC_WAKEUP, notificationCalendar!!.timeInMillis, pendingIntent)
                            Toast.makeText(this, "Medicamento agregado", Toast.LENGTH_LONG).show()
                            if (medicaments.last() == medicament) {
                                finish()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al agregar medicamento", Toast.LENGTH_LONG).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al agregar tratamiento", Toast.LENGTH_LONG).show()
            }
    }

    private fun addMedicament() {
        val inputName = findViewById<TextView>(R.id.input_medication_name)
        val inputHour = findViewById<TextView>(R.id.input_hour)
        val inputFrequency = findViewById<TextView>(R.id.input_frequency)
        val inputDose = findViewById<TextView>(R.id.input_dose)

        val name = inputName.text.toString()
        val hour = inputHour.text.toString()
        val frequency = inputFrequency.text.toString()
        val dose = inputDose.text.toString()

        if (name.isEmpty() || hour.isEmpty() || frequency.isEmpty() || dose.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        val medicament = MedicamentDto(
            name = name,
            hour = hour,
            frequency = frequency,
            dose = dose
        )

        medicaments.add(medicament)

        inputName.text = ""
        inputHour.text = ""
        inputFrequency.text = ""
        inputDose.text = ""

        Toast.makeText(this, "Medicamento agregado", Toast.LENGTH_LONG).show()
    }

    private fun navigationNavBar() {
        val btnHome = findViewById<ImageButton>(R.id.btn_home)
        btnHome.setOnClickListener {
            finish()
        }

        val btnProfile = findViewById<ImageButton>(R.id.btn_profile)
        btnProfile.setOnClickListener {
            finish()
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            goToActivity(ProfileActivity::class.java, bundle)
        }
    }

    private fun goToActivity(activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}