package com.example.baloot_saeedhonari.ui.fragments.personal

import android.R.id.message
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass


@HiltViewModel
class PersonalInfoViewModel @Inject constructor(): ViewModel() {

    val activityToStart = MutableLiveData<Intent>()

    fun OpenLink(link: String) {
        viewModelScope.launch {
            val uri = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activityToStart.postValue(intent)
        }
    }
    fun SendEmail(email: String) {
        viewModelScope.launch {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null))
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            activityToStart.postValue(intent)
        }
    }
}