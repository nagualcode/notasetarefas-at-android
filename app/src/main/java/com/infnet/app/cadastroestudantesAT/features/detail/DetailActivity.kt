package com.infnet.app.cadastroestudantesAT.features.detail

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.infnet.app.cadastroestudantesAT.R
import com.infnet.app.cadastroestudantesAT.databinding.ActivityDetailBinding
import com.infnet.app.cadastroestudantesAT.entity.Student
import com.infnet.app.cadastroestudantesAT.features.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.input_dialog.view.*

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    private lateinit var studentId:String
    lateinit var student: Student

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(getDataBinding().toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentId = intent.getLongExtra("student_id", 0).toString()

        viewModel.getStudentbyId().observe(this, Observer {
            student = it!!
            displayStudent(it)
        })

        viewModel.triggerFetchData(studentId)

    }

    fun displayStudent(student: Student?){
        getDataBinding().layoutDetail.student = student
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.delete -> {
                viewModel.deleteData(student)
                Toast.makeText(this, "Dados de  ${student.name} removidos com sucesso", Toast.LENGTH_LONG).show()
                finish()
            }
            R.id.edit -> {
                val dialogBuilder = AlertDialog.Builder(this)
                val view = layoutInflater.inflate(R.layout.input_dialog, null)
                dialogBuilder.setView(view)
                dialogBuilder.setTitle("Nova entrada")
                val et_name = view.ed_student_name
                val et_nim = view.ed_student_id
                val radioGroupGender = view.radio_group_gender
                dialogBuilder.setPositiveButton("Adicionar") { _: DialogInterface, _: Int ->
                    val studentName = et_name.text
                    val studentNim = et_nim.text
                    val gender: String
                    val selectedRadioButton = radioGroupGender.checkedRadioButtonId
                    gender = when (selectedRadioButton) {
                        R.id.radio_female -> "Feminino"
                        else -> "Masculino"
                    }
                    viewModel.updateData(student.id, studentName.toString(), studentNim.toString(), gender)
                    Toast.makeText(this, "Dados atualizados com sucesso $studentName ", Toast.LENGTH_LONG).show()
                }
                dialogBuilder.setNegativeButton("Cancelado") { _: DialogInterface, _: Int ->
                }
                dialogBuilder.show()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}
