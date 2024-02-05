package com.igo.childgenes.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentSelectBloodBinding
import com.igo.childgenes.ui.*

class SelectBloodFragment : Fragment() {

    private var _binding: FragmentSelectBloodBinding? = null
    private val binding get() = _binding!!
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_blood, container, false)
        _binding = FragmentSelectBloodBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data from bundle
        val parentPerson = requireArguments().getString(PARENT_PERSON)

        // transfer data back to mainFragment
        binding.bloodSelectCard1.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                BLOOD_TYPE to GROUP_1)
            )
            findNavController().popBackStack()
        }
        binding.bloodSelectCard2.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                BLOOD_TYPE to GROUP_2))
            findNavController().popBackStack()
        }
        binding.bloodSelectCard3.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                BLOOD_TYPE to GROUP_3))
            findNavController().popBackStack()
        }
         binding.bloodSelectCard4.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                BLOOD_TYPE to GROUP_4))
            findNavController().popBackStack()
        }
    }
}