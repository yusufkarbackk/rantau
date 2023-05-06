package com.example.rantau

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.rantau.model.RantauUser
import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

import com.google.firebase.ktx.Firebase

class RantauUserViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    private val rantauUserCollection = firestore.collection("rantau_user")

    private val _user = mutableStateOf<RantauUser?>(null)
    val user: State<RantauUser?> = _user

    fun getRantauUser(userId: String) {
        rantauUserCollection.document(userId).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<RantauUser>()
                _user.value = user
                println(documentSnapshot)
            }
    }

    fun addInitialUser(data: RantauUser) {
        firestore.collection("rantau_user")
            .document(data.userId)
            .set(data)
    }

}