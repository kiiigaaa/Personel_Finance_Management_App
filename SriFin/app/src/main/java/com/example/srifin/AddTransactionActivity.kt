package com.example.srifin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout file for this activity
        setContentView(R.layout.activity_add_transaction)

        // Add a text changed listener to the label input field
        labelInput.addTextChangedListener {
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        // Add a text changed listener to the amount input field
        amountInput.addTextChangedListener {
            if(it!!.count() > 0)
                amountLayout.error = null
        }

        // Add a click listener to the add transaction button
        addTransactionBtn.setOnClickListener {
            val label = labelInput.text.toString()
            val description = descriptionInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            // Validate the input fields
            if(label.isEmpty())
                labelLayout.error = "Please enter a valid label"

            else if(amount == null)
                amountLayout.error = "Please enter a valid amount"
            else {
                // Create a new transaction object with the input values
                val transaction  =Transaction(0, label, amount, description)
                insert(transaction)
            }
        }

        closeBtn.setOnClickListener {
            // Finish this activity and go back to the previous one
            finish()
        }
    }

    // Function to insert a new transaction into the database
    private fun insert(transaction: Transaction){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            // Function to insert a new transaction into the database
            finish()
        }
    }

}