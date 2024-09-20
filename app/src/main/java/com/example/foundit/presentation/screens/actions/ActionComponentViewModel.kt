package com.example.foundit.presentation.screens.actions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionComponentViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {
    fun sendData(phone : String,model : String,color : String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                try {
                    firestoreService.addItemData(0, phone, model, emptyMap())
                } catch (e: Exception) {
                    Log.d("addItem", "sendData: e")
                }
                firestoreService.getItemData()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}