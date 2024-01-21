package com.igo.childgenes.ui.home

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        const val PARENT_PERSON = "PARENT_PERSON"
        const val MOTHER = "Mother"
        const val FATHER = "Father"

        const val RESPOND = "RESPOND"
        const val EYES_COLOR = "EYES_COLOR"

        const val BROWN = "BROWN"
        const val GREY = "GREY"
        const val GREEN = "GREEN"
        const val NOT_SELECTED = "NOT_SELECTED"

        var eyesMotherColor = NOT_SELECTED
        var eyesFatherColor = NOT_SELECTED
    }


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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

        fillCardMotherColor()
        fillCardFatherColor()
        //checkChildRenderNeeded()


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

        // getting data when return from selectFragment
        parentFragmentManager.setFragmentResultListener(RESPOND, viewLifecycleOwner) { _, data ->
            val parentPerson = data.getString(PARENT_PERSON).toString()
            val eyesColor = data.getString(EYES_COLOR).toString()

            when (parentPerson) {
                MOTHER -> {
                    eyesMotherColor = eyesColor
                    fillCardMotherColor()
                }

                FATHER -> {
                    eyesFatherColor = eyesColor
                    fillCardFatherColor()
                }
            }
            checkChildRenderNeeded()
        }
    }

    private fun fillCardMotherColor() {
        if (eyesMotherColor == NOT_SELECTED) {
            binding.ivMotherQuestion.visibility = View.VISIBLE
        } else {
            binding.ivMotherQuestion.visibility = View.GONE
            binding.ivMotherGradientBrown.visibility = View.GONE
            binding.ivMotherGradientBlue.visibility = View.GONE
            binding.ivMotherGradientGreen.visibility = View.GONE
            when (eyesMotherColor) {
                BROWN -> binding.ivMotherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.ivMotherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.ivMotherGradientGreen.visibility = View.VISIBLE
            }
        }
    }


    private fun fillCardFatherColor() {
        if (eyesFatherColor == NOT_SELECTED) {
            binding.ivFatherQuestion.visibility = View.VISIBLE
        } else {
            binding.ivFatherQuestion.visibility = View.GONE
            binding.ivFatherGradientBrown.visibility = View.GONE
            binding.ivFatherGradientBlue.visibility = View.GONE
            binding.ivFatherGradientGreen.visibility = View.GONE
            when (eyesFatherColor) {
                BROWN -> binding.ivFatherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.ivFatherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.ivFatherGradientGreen.visibility = View.VISIBLE
//                    binding.cardFather.setCardBackgroundColor(
//                        ContextCompat.getColor(requireContext(), R.color.eyesColorBrown)
//                    )
//                    // Получите Drawable из ресурсов
//                    val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_eye_24)
//                    // Создайте копию Drawable с цветовой фильтрацией
//                    val coloredDrawable: Drawable? = drawable?.mutate()
//                    coloredDrawable?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
//                    // Установите фон как градиентный Drawable
//                    val gradientDrawable = resources.getDrawable(R.drawable.gradient_brown) as Drawable
//                    binding.ivFatherEye.background = gradientDrawable
//
//                    binding.ivFatherEye.setColorFilter(ContextCompat.getColor(requireContext(), R.color.eyesColorBrown))
            }
        }
    }

    private fun checkChildRenderNeeded() {
        if (eyesMotherColor != NOT_SELECTED && eyesFatherColor != NOT_SELECTED) countColor()
    }

    private fun countColor() {
        val parentsColor = Pair(eyesMotherColor, eyesFatherColor)
        // if (parentsColor == BROWN to BROWN) {val sfdad: Int = 23}
        Toast.makeText(requireContext(), "Colors: $parentsColor", Toast.LENGTH_SHORT).show()

        val childColor = when (parentsColor) {
            BROWN to BROWN -> Triple(75.0, 6.25, 18.75)
            GREEN to BROWN -> Triple(50.0, 12.5, 37.5)
            BROWN to GREEN -> Triple(50.0, 12.5, 37.5)
            GREY to BROWN -> Triple(50.0, 50.0, 0.0)
            BROWN to GREY -> Triple(50.0, 50.0, 0.0)
            GREEN to GREEN -> Triple(1.0, 24.5, 74.5)
            GREEN to GREY -> Triple(0.0, 50.0, 50.0)
            GREY to GREEN -> Triple(0.0, 50.0, 50.0)
            GREY to GREY -> Triple(0.0, 99.0, 1.0)
            else -> Triple(0.0, 0.0, 0.0)
        }
        displayChildColor(childColor)
    }

    private fun displayChildColor(childColor: Triple<Double, Double, Double>) {
        binding.cardChildBrown.visibility = View.GONE
        binding.cardChildGrey.visibility = View.GONE
        binding.cardChildGreen.visibility = View.GONE
        binding.messageBrown.visibility = View.GONE
        binding.messageGrey.visibility = View.GONE
        binding.messageGreen.visibility = View.GONE
        binding.progressBarBrown.visibility = View.GONE
        binding.progressBarGrey.visibility = View.GONE
        binding.progressBarGreen.visibility = View.GONE

        binding.cardChildBack.visibility = View.VISIBLE
        binding.messageChild.visibility = View.VISIBLE
        binding.messageBrown.text = "${childColor.first} %"
        binding.progressBarBrown.setProgress(childColor.first.toInt(), true)
        binding.messageGrey.text = "${childColor.second} %"
        binding.progressBarGrey.setProgress(childColor.second.toInt(), true)
        binding.messageGreen.text = "${childColor.third} %"
        binding.progressBarGreen.setProgress(childColor.third.toInt(), true)
        binding.cardExplainFolded.visibility = View.VISIBLE

        if (childColor.first != 0.0) {
            binding.cardChildBrown.visibility = View.VISIBLE
            binding.messageBrown.visibility = View.VISIBLE
            binding.progressBarBrown.visibility = View.VISIBLE
        }
        if (childColor.second != 0.0) {
            binding.cardChildGrey.visibility = View.VISIBLE
            binding.messageGrey.visibility = View.VISIBLE
            binding.progressBarGrey.visibility = View.VISIBLE
        }
        if (childColor.third != 0.0) {
            binding.cardChildGreen.visibility = View.VISIBLE
            binding.messageGreen.visibility = View.VISIBLE
            binding.progressBarGreen.visibility = View.VISIBLE
        }
    }

}