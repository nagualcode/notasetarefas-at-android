package com.infnet.app.cadastroestudantesAT.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.infnet.app.cadastroestudantesAT.features.detail.DetailActivity
import com.infnet.app.cadastroestudantesAT.databinding.StudentItemViewBinding
import com.infnet.app.cadastroestudantesAT.entity.Student
import kotlinx.android.synthetic.main.student_item_view.view.*


class ItemAdapter(private val studentList : List<Student>, val context : Context)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudentItemViewBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(studentList[position])
        holder.cardView.setOnClickListener{ v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("student_id", studentList[position].id)
            v.context.startActivity(intent)
        }
    }

    class ItemViewHolder(private val binding: StudentItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val cardView = binding.itemContainer
        fun bindView(student: Student){
            binding.student = student
        }
    }
}