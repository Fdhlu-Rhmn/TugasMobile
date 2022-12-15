package com.d121201085.aplikasimanage.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d121201085.aplikasimanage.R
import com.d121201085.aplikasimanage.databinding.FragmentUpdateBinding
import com.d121201085.aplikasimanage.model.Tugas
import com.d121201085.aplikasimanage.viewmodel.TugasViewModel
import kotlinx.coroutines.launch
import com.d121201085.aplikasimanage.databinding.FragmentAddFragmentsBinding


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTugasViewModel : TugasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        mTugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        binding.apply {
            updateJudul.setText(args.currentTask.judul)
            updateDeskripsi .setText(args.currentTask.deskripsi)
            updateCategory.setText(args.currentTask.kategori)
            updateCategory.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.Category_list, android.R.layout.simple_list_item_1))

            back.setOnClickListener {
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            updateButton.setOnClickListener{
                updateTask()
            }

            menuDelete.setOnClickListener {
                deleteTask()
            }

        }
        // Inflate the layout for this fragment
        return view
    }

    private fun updateTask() {
        binding.apply {
            val judul = updateJudul.text.toString()
            val deskripsi = updateDeskripsi.text.toString()
            val kategori = updateCategory.text.toString()

            if(inputCheck(judul, deskripsi, kategori)) {
                lifecycleScope.launch {
                    val updatedtask = Tugas(args.currentTask.id, judul, deskripsi, kategori)
                    mTugasViewModel.updateTask(updatedtask)
                    Toast.makeText(requireContext(), "Successfully Updated!", Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
            }else{
                Toast.makeText(requireContext(), "Please Fill Out All Fields!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inputCheck(title: String, description: String, category: String): Boolean {
        return !(TextUtils.isEmpty(title) && !(TextUtils.isEmpty(description) && !(TextUtils.isEmpty(category))))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mTugasViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully Removed Task",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment )
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete Task?")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.create().show()
    }
}