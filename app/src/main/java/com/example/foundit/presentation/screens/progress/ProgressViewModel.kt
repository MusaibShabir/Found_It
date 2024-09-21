package com.example.foundit.presentation.screens.progress

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foundit.presentation.data.firestore.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val firestoreService: FirestoreService
) : ViewModel() {

    private val _haltedItems = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val haltedItems: StateFlow<List<Map<String, Any>>> = _haltedItems.asStateFlow()

    private val _inProcessItems = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val inProcessItems: StateFlow<List<Map<String, Any>>> = _inProcessItems.asStateFlow()

    private val _finishedItems = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val finishedItems: StateFlow<List<Map<String, Any>>> = _finishedItems.asStateFlow()

    init {
        fetchData()
        Log.d("progress", "init called")
    }

    private fun fetchData() {
        viewModelScope.launch {
            // Wait until the current user ID is available
            while (firestoreService.currentUserId.isEmpty()) {
                delay(100)
            }

            firestoreService.getCardData().collect { items ->
                // Log the raw items fetched from Firestore
                Log.d("progress", "Raw items: $items")

                // Sort by phone (replace "phone" with the actual field name if necessary)
                val sortedItems = items//.sortedBy { it["date"]?.toString() }

                // Log the sorted items
                Log.d("progress", "Sorted items: $sortedItems")

                // Filter based on status (-1 = halted, 0 = in-process, 1 = finished)
                val halted = sortedItems.filter { (it["status"] as? Long) == -1L }
                val inProcess = sortedItems.filter { (it["status"] as? Long) == 0L }
                val finished = sortedItems.filter { (it["status"] as? Long) == 1L }

                // Update StateFlow values
                _haltedItems.value = halted
                _inProcessItems.value = inProcess
                _finishedItems.value = finished

                // Log the final lists
                Log.d("progress", "Halted items: $halted")
                Log.d("progress", "In-process items: $inProcess")
                Log.d("progress", "Finished items: $finished")
            }
        }
    }

    /*
    override fun onCleared() {
        super.onCleared()
        Log.d("Progress", "ViewModel destroyed")
    }
    
     */
}