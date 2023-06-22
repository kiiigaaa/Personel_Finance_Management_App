package com.example.srifin

import androidx.room.Database
import androidx.room.RoomDatabase

// Define the database with the @Database annotation
@Database(entities = arrayOf(Transaction::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Declare an abstract function to return the DAO (Data Access Object) for the Transaction class
    abstract fun transactionDao() : TransactionDao
}