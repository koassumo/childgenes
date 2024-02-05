package com.igo.childgenes.ui.rh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentSelectRhBinding
import com.igo.childgenes.ui.*

class SelectRhFragment : Fragment() {

    private var _binding: FragmentSelectRhBinding? = null
    private val binding get() = _binding!!
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_rh, container, false)
        _binding = FragmentSelectRhBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data from bundle
        val parentPerson = requireArguments().getString(PARENT_PERSON)

        // transfer data back to mainFragment
        binding.rhSelectCard1.setOnClickListener{
            parentFragmentManager.setFragmentResult(
                RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                RH_TYPE to RH_PLUS)
            )
            findNavController().popBackStack()
        } 
        binding.rhSelectCard2.setOnClickListener{
            parentFragmentManager.setFragmentResult(
                RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                RH_TYPE to RH_MINUS)
            )
            findNavController().popBackStack()
        }
    }
}