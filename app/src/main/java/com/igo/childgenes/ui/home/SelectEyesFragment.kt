package com.igo.childgenes.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentSelectEyesBinding
import com.igo.childgenes.ui.home.HomeFragment.Companion

class SelectEyesFragment : Fragment() {

    private var _binding: FragmentSelectEyesBinding? = null
    private val binding get() = _binding!!
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_select_eyes, container, false)
        _binding = FragmentSelectEyesBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data from bundle
        val parentPerson = requireArguments().getString(HomeFragment.PARENT_PERSON)
        // binding.messageSelect.text = "Select $parentPerson's Eyes Color"
        //Toast.makeText(context, "Ваш текст сообщения $parentPerson ", Toast.LENGTH_SHORT).show()

        // transfer data back to mainFragment
        binding.cardBrown.setOnClickListener{
            parentFragmentManager.setFragmentResult(HomeFragment.RESPOND, bundleOf(
                HomeFragment.PARENT_PERSON to parentPerson,
                HomeFragment.EYES_COLOR to HomeFragment.BROWN)
            )
            findNavController().popBackStack()
        }
        binding.cardGrey.setOnClickListener{
            parentFragmentManager.setFragmentResult(HomeFragment.RESPOND, bundleOf(
                HomeFragment.PARENT_PERSON to parentPerson,
                HomeFragment.EYES_COLOR to HomeFragment.GREY))
            findNavController().popBackStack()
        }
        binding.cardGreen.setOnClickListener{
            parentFragmentManager.setFragmentResult(HomeFragment.RESPOND, bundleOf(
                HomeFragment.PARENT_PERSON to parentPerson,
                HomeFragment.EYES_COLOR to HomeFragment.GREEN))
            findNavController().popBackStack()
        }
    }

}