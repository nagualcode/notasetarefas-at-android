package com.infnet.app.cadastroestudantesAT.features.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.infnet.app.cadastroestudantesAT.db.repository.LocalRepository
import com.infnet.app.cadastroestudantesAT.entity.Student
import com.infnet.app.cadastroestudantesAT.features.base.BaseViewModel


class MainActivityViewModel @ViewModelInject constructor(private val localRepository: LocalRepository) : BaseViewModel() {

    private var mTriggerFetchData = MutableLiveData<Boolean>()
    private var student : LiveData<List<Student>> = Transformations.switchMap(mTriggerFetchData){
        localRepository.getAllData()
    }

    fun insertStudent(student: Student){
        localRepository.insertData(student)
    }

    fun getStudents(): LiveData<List<Student>>{
        return student
    }

    fun loadStudent() {
        mTriggerFetchData.value = true
    }
}