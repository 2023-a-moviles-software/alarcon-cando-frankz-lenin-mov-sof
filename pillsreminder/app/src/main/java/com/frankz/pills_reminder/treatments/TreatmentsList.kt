package com.frankz.pills_reminder.treatments

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frankz.pills_reminder.MainActivity
import com.frankz.pills_reminder.R
import com.frankz.pills_reminder.adapters.TreatmentItemAdapter
import com.frankz.pills_reminder.dtos.MedicamentDto
import com.frankz.pills_reminder.models.Medicament
import com.frankz.pills_reminder.models.Treatment
import com.frankz.pills_reminder.models.User
import com.frankz.pills_reminder.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TreatmentsList : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var user: User? = null
    private val treatments = arrayListOf<Treatment>()
    private val recyclerViewTreatments: RecyclerView by lazy {
        findViewById(R.id.rv_treatments)
    }

    private val getIfUserHasBeenUpdated = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val hasBeenUpdated = data?.getBooleanExtra("hasBeenEdited", false)
            if (hasBeenUpdated == true) {
                val updatedUser = data?.getParcelableExtra<User>("user")
                if (user != null) {
                    user = updatedUser
                }
            } else {
                println("No hay usuario")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatments_list)

        val isLogged = auth.currentUser != null
        if (!isLogged) {
            finish()
            goToActivity(MainActivity::class.java)
            return
        }

        user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            println(user!!.name)
            println(user!!.email)
        } else {
            println("No hay usuario")
        }

//        val txt = findViewById<TextView>(R.id.txt_test)
        if (user != null) {
            // txt.text = user!!.name
        }

        println("Recycler view: $recyclerViewTreatments")

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()

        navigationNavBar()
    }

    override fun onResume() {
        super.onResume()
        getTreatments()
    }


    private fun tranformToObject(item: QueryDocumentSnapshot) {
        val id = item.id
        val disease = item.data["disease"] as String
        val description = item.data["description"] as String
        val initialDate = item.data["initialDate"] as String
        val finalDate = item.data["finalDate"] as String
        val treatment = Treatment(
            id,
            disease,
            description,
            initialDate,
            finalDate,
            ArrayList()
        )
        db.collection("treatments")
            .document(id)
            .collection("medicaments")
            .get()
            .addOnSuccessListener {
                for (medicament in it) {
                    val id = medicament.id
                    val name = medicament.data["name"] as String
                    val frequency = medicament.data["frequency"] as String
                    val hour = medicament.data["hour"] as String
                    val dose = medicament.data["dose"] as String
                    val medicament = Medicament(id, name, hour, frequency, dose)
                    treatment.medicaments.add(medicament)
                    treatments.add(treatment)
                }

                val treatmentsAdapter = TreatmentItemAdapter(
                    this,
                    treatments,
                    recyclerViewTreatments
                )

                recyclerViewTreatments.adapter = treatmentsAdapter

                recyclerViewTreatments.itemAnimator = DefaultItemAnimator()
                recyclerViewTreatments.layoutManager = LinearLayoutManager(this)

                treatmentsAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    private fun getTreatments() {
        db.collection("treatments")
            .whereEqualTo("userId", user!!.id)
            .get()
            .addOnSuccessListener { documents ->
                treatments.clear()
                for (document in documents) {
                    println("${document.id} => ${document.data}")
                    tranformToObject(document)
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    private fun navigationNavBar() {
        val btnPills = findViewById<ImageButton>(R.id.btn_new_treatment)
        btnPills.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            goToActivity(NewTreatment::class.java, bundle)
        }

        val btnProfile = findViewById<ImageButton>(R.id.btn_profile)
        btnProfile.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            goToActivity(ProfileActivity::class.java, bundle, true)
        }
    }

    private fun goToActivity(activity: Class<*>, bundle: Bundle? = null, withCallback: Boolean = false) {
        val intent = Intent(this, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        if (withCallback) {
            getIfUserHasBeenUpdated.launch(intent)
        } else {
            startActivity(intent)
        }
    }
}