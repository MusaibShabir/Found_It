package com.example.foundit.presentation.screens.actions

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
                firestoreService.addItemData(phone, model, color)
                firestoreService.getItemData()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}