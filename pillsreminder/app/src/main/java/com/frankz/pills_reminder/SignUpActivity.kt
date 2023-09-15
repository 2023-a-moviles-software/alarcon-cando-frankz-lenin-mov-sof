package com.frankz.pills_reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.frankz.pills_reminder.dtos.UserDto
import com.frankz.pills_reminder.profile.ProfileActivity
import com.frankz.pills_reminder.treatments.NewTreatment
import com.frankz.pills_reminder.treatments.TreatmentsList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnLoginTab = findViewById<Button>(R.id.btn_login_tab)
        btnLoginTab.setOnClickListener {
            finish()
        }

        val btnRegister = findViewById<Button>(R.id.btn_sign_up)
        btnRegister.setOnClickListener {
            register()
        }
    }

    override fun onStart() {
        super.onStart()
        val isLogged = auth.currentUser != null

        if (isLogged) {
            finish()
            goToActivity(TreatmentsList::class.java)
            return
        }
    }

    private fun register() {
        val name = findViewById<EditText>(R.id.input_name).text.toString()
        val phone = findViewById<EditText>(R.id.input_phone).text.toString()
        val email = findViewById<EditText>(R.id.input_email).text.toString()
        val password = findViewById<EditText>(R.id.input_password).text.toString()

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    saveUserData(UserDto(name, phone, email))
                } else {
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserData(data: UserDto) {
        val user = hashMapOf(
            "name" to data.name,
            "phone" to data.phone,
            "email" to data.email
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                goToActivity(TreatmentsList::class.java)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_LONG).show()
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