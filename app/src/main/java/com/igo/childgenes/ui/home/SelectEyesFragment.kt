package com.igo.childgenes.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentSelectEyesBinding
import com.igo.childgenes.ui.*

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
        val parentPerson = requireArguments().getString(PARENT_PERSON)
        // binding.messageSelect.text = "Select $parentPerson's Eyes Color"
        //Toast.makeText(context, "Ваш текст сообщения $parentPerson ", Toast.LENGTH_SHORT).show()

        // transfer data back to mainFragment
        binding.eyeSelectCardBrown.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                EYES_COLOR to BROWN)
            )
            findNavController().popBackStack()
        }
        binding.eyeSelectCardGrey.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                EYES_COLOR to GREY))
            findNavController().popBackStack()
        }
        binding.eyeSelectCardGreen.setOnClickListener{
            parentFragmentManager.setFragmentResult(RESPOND, bundleOf(
                PARENT_PERSON to parentPerson,
                EYES_COLOR to GREEN))
            findNavController().popBackStack()
        }
    }

}