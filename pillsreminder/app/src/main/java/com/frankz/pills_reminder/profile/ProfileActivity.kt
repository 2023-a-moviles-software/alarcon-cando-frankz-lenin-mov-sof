package com.frankz.pills_reminder.profile

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
import com.frankz.pills_reminder.models.User
import com.frankz.pills_reminder.treatments.NewTreatment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var user: User? = null
    private var isEditing = false
    private var hasBeenEdited = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val isLogged = auth.currentUser != null
        if (!isLogged) {
            finish()
            goToActivity(MainActivity::class.java)
            return
        }

        user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            loadUserData(user!!)
        }

        val btnEditInformation = findViewById<Button>(R.id.btn_edit_profile)
        btnEditInformation.setOnClickListener {
            toggleEditInformation(btnEditInformation)
        }

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            auth.signOut()
            finish()
            goToActivity(MainActivity::class.java)
        }

        navigationNavBar()
    }

    private fun loadUserData(user: User) {
        val inputName = findViewById<EditText>(R.id.input_name)
        val inputPhone = findViewById<EditText>(R.id.input_phone)
        val inputEmail = findViewById<EditText>(R.id.input_email)

        inputName.isEnabled = false
        inputPhone.isEnabled = false
        inputEmail.isEnabled = false

        inputName.setText(user.name)
        inputPhone.setText(user.phone)
        inputEmail.setText(user.email)
    }

    private fun toggleEditInformation(btnEditInformation: Button) {
        val inputName = findViewById<EditText>(R.id.input_name)
        val inputPhone = findViewById<EditText>(R.id.input_phone)
        val inputEmail = findViewById<EditText>(R.id.input_email)
        val txtState = findViewById<TextView>(R.id.txt_state)

        if (!isEditing) {
            inputName.isEnabled = true
            inputPhone.isEnabled = true
            inputEmail.isEnabled = true
            txtState.text = "Modo Edición"

            isEditing = true
            btnEditInformation.text = "Guardar"
            btnEditInformation.setBackgroundColor(getColor(R.color.green_color))
        } else {
            inputName.isEnabled = false
            inputPhone.isEnabled = false
            inputEmail.isEnabled = false
            txtState.text = "Modo Vista"

            val name = inputName.text.toString()
            val phone = inputPhone.text.toString()
            val email = inputEmail.text.toString()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
                return
            }

            if (name == user!!.name && phone == user!!.phone && email == user!!.email) {
                Toast.makeText(this, "No se ha modificado ningún campo", Toast.LENGTH_SHORT).show()
                return
            }

            val updatedData = hashMapOf(
                "name" to name,
                "phone" to phone,
                "email" to email
            )

            val id = user!!.id

            if (id != null) {
                db.collection("users")
                    .document(id)
                    .set(updatedData)
                    .addOnSuccessListener {
                        user!!.name = name
                        user!!.phone = phone
                        user!!.email = email
                        isEditing = false
                        btnEditInformation.text = "Editar Información"
                        btnEditInformation.setBackgroundColor(getColor(R.color.yellow_color))
                        hasBeenEdited = true
                        Toast.makeText(this, "Información actualizada - $hasBeenEdited", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigationNavBar() {
        val btnHome = findViewById<ImageButton>(R.id.btn_home)
        btnHome.setOnClickListener {
            if (hasBeenEdited) {
                val intent = Intent()
                intent.putExtra("hasBeenEdited", hasBeenEdited)
                intent.putExtra("user", user)
                setResult(RESULT_OK, intent)
            }
            finish()
        }

        val btnPills = findViewById<ImageButton>(R.id.btn_new_treatment)
        btnPills.setOnClickListener {
            finish()
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            goToActivity(NewTreatment::class.java, bundle)
        }
    }

    fun goToActivity(activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}