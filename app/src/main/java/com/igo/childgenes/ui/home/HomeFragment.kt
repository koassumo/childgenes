package com.igo.childgenes.ui.home

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.EyeSegmentKinderBinding
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

    private var _bindingSegKinder: EyeSegmentKinderBinding? = null
    private val bindingSegKinder get() = _bindingSegKinder!!


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _bindingSegKinder = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _bindingSegKinder = EyeSegmentKinderBinding.inflate(inflater, container, false)
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

        bindingSegKinder.eyeMessageBrown.text = "%"
        binding.eyeSubtitleMessageFather.text= "lfdskj"

        val ffff =
        // Получение ссылки на вложенную TextView



        // Теперь вы можете работать с textViewInOtherLayout
        //textViewInOtherLayout.text = "Updated text"




        binding.eyeCardMother.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to MOTHER)
            )
        }
        binding.eyeCardFather.setOnClickListener {
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
            binding.eyeIvMotherQuestion.visibility = View.VISIBLE
        } else {
            binding.eyeIvMotherQuestion.visibility = View.GONE
            binding.eyeIvMotherGradientBrown.visibility = View.GONE
            binding.eyeIvMotherGradientBlue.visibility = View.GONE
            binding.eyeIvMotherGradientGreen.visibility = View.GONE
            when (eyesMotherColor) {
                BROWN -> binding.eyeIvMotherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvMotherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvMotherGradientGreen.visibility = View.VISIBLE
            }
        }
    }


    private fun fillCardFatherColor() {
        if (eyesFatherColor == NOT_SELECTED) {
            binding.eyeIvFatherQuestion.visibility = View.VISIBLE
        } else {
            binding.eyeIvFatherQuestion.visibility = View.GONE
            binding.eyeIvFatherGradientBrown.visibility = View.GONE
            binding.eyeIvFatherGradientBlue.visibility = View.GONE
            binding.eyeIvFatherGradientGreen.visibility = View.GONE
            when (eyesFatherColor) {
                BROWN -> binding.eyeIvFatherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvFatherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvFatherGradientGreen.visibility = View.VISIBLE
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
        bindingSegKinder.eyeCardChildBrown.visibility = View.GONE
        bindingSegKinder.eyeCardChildGrey.visibility = View.GONE
        bindingSegKinder.eyeCardChildGreen.visibility = View.GONE
        bindingSegKinder.eyeMessageBrown.visibility = View.GONE
        bindingSegKinder.eyeMessageGrey.visibility = View.GONE
        bindingSegKinder.eyeMessageGreen.visibility = View.GONE
        bindingSegKinder.eyeProgressBarBrown.visibility = View.GONE
        bindingSegKinder.eyeProgressBarGrey.visibility = View.GONE
        bindingSegKinder.eyeProgressBarGreen.visibility = View.GONE

        binding.eyeSegmentGrandparents.visibility = View.VISIBLE
        binding.eyeSegmentChild.visibility = View.VISIBLE

        bindingSegKinder.eyeMessageChild.visibility = View.VISIBLE
        bindingSegKinder.eyeMessageBrown.text = "${childColor.first} %"
        bindingSegKinder.eyeProgressBarBrown.setProgress(childColor.first.toInt(), true)
        bindingSegKinder.eyeMessageGrey.text = "${childColor.second} %"
        bindingSegKinder.eyeProgressBarGrey.setProgress(childColor.second.toInt(), true)
        bindingSegKinder.eyeMessageGreen.text = "${childColor.third} %"
        bindingSegKinder.eyeProgressBarGreen.setProgress(childColor.third.toInt(), true)
        //bindingSegKinder.eyeCardExplainFolded.visibility = View.VISIBLE

        if (childColor.first != 0.0) {
            bindingSegKinder.eyeCardChildBrown.visibility = View.GONE
            bindingSegKinder.eyeMessageBrown.visibility = View.GONE
            bindingSegKinder.eyeProgressBarBrown.visibility = View.GONE
        }
        if (childColor.second != 0.0) {
            bindingSegKinder.eyeCardChildGrey.visibility = View.VISIBLE
            bindingSegKinder.eyeMessageGrey.visibility = View.VISIBLE
            bindingSegKinder.eyeProgressBarGrey.visibility = View.VISIBLE
        }
        if (childColor.third != 0.0) {
            bindingSegKinder.eyeCardChildGreen.visibility = View.VISIBLE
            bindingSegKinder.eyeMessageGreen.visibility = View.VISIBLE
            bindingSegKinder.eyeProgressBarGreen.visibility = View.VISIBLE
        }
    }

}