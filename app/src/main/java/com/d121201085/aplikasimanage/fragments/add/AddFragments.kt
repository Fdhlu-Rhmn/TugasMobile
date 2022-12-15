package com.d121201085.aplikasimanage.fragments.add

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
import com.d121201085.aplikasimanage.R
import com.d121201085.aplikasimanage.model.Tugas
import com.d121201085.aplikasimanage.viewmodel.TugasViewModel
import com.d121201085.aplikasimanage.databinding.FragmentAddFragmentsBinding
import kotlinx.coroutines.launch

class AddFragments : Fragment() {
    private var _binding: FragmentAddFragmentsBinding? = null
    private val binding get() = _binding!!

    lateinit var taskCategory: ArrayAdapter<CharSequence>
    private lateinit var mTugasViewModel : TugasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFragmentsBinding.inflate(inflater, container, false)
        val view = binding.root

        mTugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        setDropDown()
        binding.apply {
            back.setOnClickListener {
                findNavController().navigate(R.id.action_addFragments_to_listFragment)
            }
            addBtn.setOnClickListener{
                insertDataToDatabase()
            }
        }

        return view
    }

    private fun setDropDown() {
        taskCategory = ArrayAdapter.createFromResource(requireContext(),R.array.Category_list, android.R.layout.simple_list_item_1)
        binding.autoCategory.setAdapter(taskCategory)
    }
    private fun insertDataToDatabase() {
        binding.apply {
            val judul = addJudul.text.toString()
            val deskripsi = addJudul.text.toString()
            val kategori = autoCategory.text.toString()

            if(inputCheck(judul, deskripsi, kategori)) {
                lifecycleScope.launch {
                    val task = Tugas(0,judul, deskripsi, kategori)
                    mTugasViewModel.addTugas(task)
                    Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_addFragments_to_listFragment)
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
}