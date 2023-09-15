package com.frankz.pills_reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.frankz.pills_reminder.models.User
import com.frankz.pills_reminder.treatments.TreatmentsList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "treatments" // Debe ser único para cada canal
            val channelName = "Treatments"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val btnSignUpTab = findViewById<Button>(R.id.btn_signup_tab)
        btnSignUpTab.setOnClickListener {
            goToActivity(SignUpActivity::class.java)
        }

        val btnLogin = findViewById<Button>(R.id.btn_sign_up)
        btnLogin.setOnClickListener {
            login()
        }
    }

    override fun onStart() {
        super.onStart()

        val isLogged = auth.currentUser != null

        if (isLogged) {
            getUser()
            return
        }
    }

    fun login() {
        val email = findViewById<EditText>(R.id.input_email).text.toString()
        val password = findViewById<EditText>(R.id.input_password).text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    getUser()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun getUser() {
        val user = auth.currentUser
        if (user != null) {
            val email = user.email
            db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val id = document.id
                        val name = document.data["name"]
                        val phone = document.data["phone"]
                        val email = document.data["email"]

                        val bundle = Bundle()

                        bundle.putParcelable("user", User(id, name.toString(), phone.toString(), email.toString()))
                        goToActivity(TreatmentsList::class.java, bundle)
                    }
                }
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