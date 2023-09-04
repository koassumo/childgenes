package com.igo.childgenes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        const val PARENT_PERSON = "PARENT_PERSON"
        const val NOT_SELECTED = "NOT_SELECTED"
        const val BROWN = "BROWN"
        const val GREY = "GREY"
        const val GREEN = "GREEN"
        const val MOTHER = "MOTHER"
        const val FATHER = "FATHER"

        var eyesMotherColor = NOT_SELECTED
        var eyesFatherColor = NOT_SELECTED
    }


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardMother.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to MOTHER)
            )
        }
        binding.cardFather.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to FATHER)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}