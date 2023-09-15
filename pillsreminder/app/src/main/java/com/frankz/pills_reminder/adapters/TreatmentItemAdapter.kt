package com.frankz.pills_reminder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.frankz.pills_reminder.R
import com.frankz.pills_reminder.models.Treatment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TreatmentItemAdapter(
    private val context: AppCompatActivity,
    private val treatmentItemsList: ArrayList<Treatment>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<TreatmentItemAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val treatmentName: TextView
            val listViewMedicaments: ListView
            val initDate: TextView
            val endDate: TextView
            val btnDelete: ImageButton

            init {
                treatmentName = view.findViewById(R.id.txt_treatment)
                listViewMedicaments = view.findViewById(R.id.lv_medicaments)
                initDate = view.findViewById(R.id.txt_init_date)
                endDate = view.findViewById(R.id.txt_end_date)
                btnDelete = view.findViewById(R.id.btn_delete_treatment)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.treatment_card,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = treatmentItemsList[position]

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            item.medicaments
        )

        holder.listViewMedicaments.adapter = adapter

        adapter.notifyDataSetChanged()

        holder.treatmentName.text = item.disease
        holder.initDate.text = item.initialDate
        holder.endDate.text = item.finalDate

        holder.btnDelete.setOnClickListener {
            removeItem(position)
        }
    }

    private fun removeItem(position: Int) {
        val treatmentToDelete = treatmentItemsList[position]
        val builder = AlertDialog.Builder(context)
        builder.setTitle("¿Estás seguro de eliminar: ${treatmentToDelete.disease}?")
        builder.setMessage("Una vez eliminado no se podrá recuperar.")
        builder.setPositiveButton("Sí") { _, _ ->
            val db = Firebase.firestore
            db.collection("treatments")
                .document(treatmentItemsList[position].id)
                .delete()
                .addOnSuccessListener {
                    treatmentItemsList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, treatmentItemsList.size)

                }
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    override fun getItemCount(): Int {
        return this.treatmentItemsList.size
    }
}