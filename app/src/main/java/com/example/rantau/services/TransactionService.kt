package com.example.rantau.services

import com.example.rantau.model.Transaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TransactionService {
    private val db = Firebase.firestore

    fun getAllTransactions(userId: String) {
        db.collection("transactions")
            .whereEqualTo("userId", userId)
            .get()
    }

    fun addTransaction(data: Transaction, userId: String) {
        db.collection("transactions")
            .document(userId)
            .set(data)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                println("Filed to upload document: $e")
            }

    }
}