package com.d121201085.aplikasimanage.fragments.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.d121201085.aplikasimanage.R
import com.d121201085.aplikasimanage.model.Tugas
import com.d121201085.aplikasimanage.viewmodel.TugasViewModel
import com.d121201085.aplikasimanage.fragments.add.AddFragments


class ListAdapter: RecyclerView.Adapter<ListAdapter.TugasViewHolder>() {

    private var tugasList = emptyList<Tugas>()
    private lateinit var mTugasViewModel : TugasViewModel
    private var context: Context? = null

    class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judul: TextView = itemView.findViewById(R.id.judul_txt)
        val deskripsi:TextView = itemView.findViewById(R.id.deskripsi_txt)
        val kategori:TextView = itemView.findViewById(R.id.category)
        val layout: ConstraintLayout = itemView.findViewById(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        context = parent.context
        mTugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rowlist, parent, false)
        return TugasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val currentTask = tugasList[position]
        holder.judul.text = currentTask.judul
        holder.deskripsi.text = currentTask.deskripsi
        holder.kategori.text = currentTask.kategori
        when(currentTask.kategori) {
            context!!.resources.getStringArray(R.array.Category_list)[0] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.red))
            }
            context!!.resources.getStringArray(R.array.Category_list)[1] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.blue_700))
            }
            context!!.resources.getStringArray(R.array.Category_list)[2] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.yellow))
            }
            context!!.resources.getStringArray(R.array.Category_list)[3] -> {
                holder.kategori.backgroundTintList = ColorStateList.valueOf(context!!.resources.getColor(R.color.green))
            }
        }
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,AddFragments::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
        holder.layout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentTask)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(tugas: List<Tugas>) {
        this.tugasList = tugas
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tugasList.size
    }
}