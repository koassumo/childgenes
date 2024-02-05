package com.igo.childgenes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentHomeBinding
import kotlin.math.roundToInt
import com.igo.childgenes.ui.*

class HomeFragment : Fragment() {

    companion object {
        var eyesMotherColor = NOT_SELECTED
        var eyesFatherColor = NOT_SELECTED
        var eyesMotherGrannyColor = NOT_SELECTED
        var eyesMotherGrandpaColor = NOT_SELECTED
        var eyesFatherGrannyColor = NOT_SELECTED
        var eyesFatherGrandpaColor = NOT_SELECTED
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

    override fun onResume() {
        super.onResume()
        if (eyesMotherColor == NOT_SELECTED || eyesFatherColor == NOT_SELECTED) {
            hideSegmentGrandparents()
            hideSegmentChild()
        }
        fillCardMotherColor()
        fillCardFatherColor()
        fillCardMotherGrannyColor()
        fillCardMotherGrandpaColor()
        fillCardFatherGrannyColor()
        fillCardFatherGrandpaColor()
        checkChildRenderNeeded()
        checkBtnGrandHide()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toast.makeText(requireContext(), "I am here!!!", Toast.LENGTH_SHORT).show()
        //hideSegmentGrandparents()
        //hideSegmentChild()

        //checkChildRenderNeeded()

        binding.eyeCardMother.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to MOTHER)
            )
        }
        binding.eyeCardMotherGranny.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to MOTHER_GRANNY)
            )
        }
        binding.eyeCardMotherGrandpa.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to MOTHER_GRANDPA)
            )
        }
        binding.eyeCardFather.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to FATHER)
            )
        }
        binding.eyeCardFatherGranny.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to FATHER_GRANNY)
            )
        }

        binding.eyeCardFatherGrandpa.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_home_to_select_eyes_fragment,
                bundleOf(PARENT_PERSON to FATHER_GRANDPA)
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

                MOTHER_GRANNY -> {
                    eyesMotherGrannyColor = eyesColor
                    fillCardMotherGrannyColor()
                }

                MOTHER_GRANDPA -> {
                    eyesMotherGrandpaColor = eyesColor
                    fillCardMotherGrandpaColor()
                }

                FATHER -> {
                    eyesFatherColor = eyesColor
                    fillCardFatherColor()
                }

                FATHER_GRANNY -> {
                    eyesFatherGrannyColor = eyesColor
                    fillCardFatherGrannyColor()
                }

                FATHER_GRANDPA -> {
                    eyesFatherGrandpaColor = eyesColor
                    fillCardFatherGrandpaColor()
                }
            }
            checkChildRenderNeeded()
        }

        binding.eyeInfo1Btn.setOnClickListener {
            if (binding.eyeInfo1ArrowUp.visibility == View.INVISIBLE) {
                binding.eyeInfo1Content.visibility = View.VISIBLE
                binding.eyeInfo1ArrowDown.visibility = View.INVISIBLE
                binding.eyeInfo1ArrowUp.visibility = View.VISIBLE
            } else {
                binding.eyeInfo1Content.visibility = View.GONE
                binding.eyeInfo1ArrowUp.visibility = View.INVISIBLE
                binding.eyeInfo1ArrowDown.visibility = View.VISIBLE
            }
        }




        binding.eyeBtnAddGrandparents.setOnClickListener {
            scrollUp()
        }
    }


    private fun fillCardMotherColor() {
        if (eyesMotherColor == NOT_SELECTED) {
            //binding.eyeIvMotherQuestion.visibility = View.VISIBLE
        } else {
            binding.eyeIvMotherQuestion.visibility = View.GONE
            binding.eyeIvMotherGradientBrown.visibility = View.GONE
            binding.eyeIvMotherGradientBlue.visibility = View.GONE
            binding.eyeIvMotherGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeMotherIconHint, eyesMotherColor)
            when (eyesMotherColor) {
                BROWN -> binding.eyeIvMotherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvMotherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvMotherGradientGreen.visibility = View.VISIBLE
            }
        }
    }


    private fun fillCardFatherColor() {
        Toast.makeText(requireContext(), "fillCardFatherColor", Toast.LENGTH_SHORT).show()
        if (eyesFatherColor == NOT_SELECTED) {
            //binding.eyeIvFatherQuestion.visibility = View.VISIBLE
        } else {
            binding.eyeIvFatherQuestion.visibility = View.GONE
            binding.eyeIvFatherGradientBrown.visibility = View.GONE
            binding.eyeIvFatherGradientBlue.visibility = View.GONE
            binding.eyeIvFatherGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeFatherIconHint, eyesFatherColor)
            when (eyesFatherColor) {
                BROWN -> binding.eyeIvFatherGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvFatherGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvFatherGradientGreen.visibility = View.VISIBLE
            }
        }
    }
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

    private fun fillCardMotherGrannyColor() {
        if (eyesMotherGrannyColor != NOT_SELECTED) {
            binding.eyeIvMotherGrannyGradientBrown.visibility = View.GONE
            binding.eyeIvMotherGrannyGradientBlue.visibility = View.GONE
            binding.eyeIvMotherGrannyGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeMotherGrannyIconHint, eyesMotherGrannyColor)
            when (eyesMotherGrannyColor) {
                BROWN -> binding.eyeIvMotherGrannyGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvMotherGrannyGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvMotherGrannyGradientGreen.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardMotherGrandpaColor() {
        if (eyesMotherGrandpaColor != NOT_SELECTED) {
            binding.eyeIvMotherGrandpaGradientBrown.visibility = View.GONE
            binding.eyeIvMotherGrandpaGradientBlue.visibility = View.GONE
            binding.eyeIvMotherGrandpaGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeMotherGrandpaIconHint, eyesMotherGrandpaColor)
            when (eyesMotherGrandpaColor) {
                BROWN -> binding.eyeIvMotherGrandpaGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvMotherGrandpaGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvMotherGrandpaGradientGreen.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFatherGrannyColor() {
        if (eyesFatherGrannyColor != NOT_SELECTED) {
            binding.eyeIvFatherGrannyGradientBrown.visibility = View.GONE
            binding.eyeIvFatherGrannyGradientBlue.visibility = View.GONE
            binding.eyeIvFatherGrannyGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeFatherGrannyIconHint, eyesFatherGrannyColor)
            when (eyesFatherGrannyColor) {
                BROWN -> binding.eyeIvFatherGrannyGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvFatherGrannyGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvFatherGrannyGradientGreen.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFatherGrandpaColor() {
        if (eyesFatherGrandpaColor != NOT_SELECTED) {
            binding.eyeIvFatherGrandpaGradientBrown.visibility = View.GONE
            binding.eyeIvFatherGrandpaGradientBlue.visibility = View.GONE
            binding.eyeIvFatherGrandpaGradientGreen.visibility = View.GONE
            changeCardHint(binding.eyeFatherGrandpaIconHint, eyesFatherGrandpaColor)
            when (eyesFatherGrandpaColor) {
                BROWN -> binding.eyeIvFatherGrandpaGradientBrown.visibility = View.VISIBLE
                GREY -> binding.eyeIvFatherGrandpaGradientBlue.visibility = View.VISIBLE
                GREEN -> binding.eyeIvFatherGrandpaGradientGreen.visibility = View.VISIBLE
            }
        }
    }

    private fun checkChildRenderNeeded() {
        if (eyesMotherColor != NOT_SELECTED && eyesFatherColor != NOT_SELECTED) {
            countColor()
            checkBtnGrandHide()
            scrollDown()
            unHideSegmentGrandparents()
        }
    }

    private fun checkBtnGrandHide() {
        if ((eyesMotherColor != NOT_SELECTED && eyesFatherColor != NOT_SELECTED) &&
            (eyesMotherGrannyColor == NOT_SELECTED || eyesMotherGrandpaColor == NOT_SELECTED ||
                    eyesFatherGrannyColor == NOT_SELECTED || eyesFatherGrannyColor == NOT_SELECTED
                    )
        ) {
            binding.eyeBtnAddGrandparents.visibility = View.VISIBLE
        } else {
            binding.eyeBtnAddGrandparents.visibility = View.GONE
        }
    }

    private fun countColor() {

        // if (parentsColor == com.igo.childgenes.ui.BROWN to com.igo.childgenes.ui.BROWN) {val sfdad: Int = 23}
        //Toast.makeText(requireContext(), "Colors: $parentsColor", Toast.LENGTH_SHORT).show()

        val childColor = proportionForHeir(Pair(eyesMotherColor, eyesFatherColor))
        val motherColor = proportionForHeir(Pair(eyesMotherGrannyColor, eyesMotherGrandpaColor))
        val fatherColor = proportionForHeir(Pair(eyesFatherGrannyColor, eyesFatherGrandpaColor))

        val averageParentsColor = Triple(
            (motherColor.first + fatherColor.first) / 2,
            (motherColor.second + fatherColor.second) / 2,
            (motherColor.third + fatherColor.third) / 2,
        )

        var childColorConsideringParents = Triple(
            childColor.first * (1 + averageParentsColor.first / 100),
            childColor.second * (1 + averageParentsColor.second / 100),
            childColor.third * (1 + averageParentsColor.third / 100)
        )

        val onePercent =
            (childColorConsideringParents.first + childColorConsideringParents.second + childColorConsideringParents.third) / 100

//        childColorConsideringParents = Triple (
//            childColorConsideringParents.first / onePercent,
//            childColorConsideringParents.second / onePercent,
//            childColorConsideringParents.third / onePercent,
//        )

//        childColorConsideringParents = Triple (
//            "%.0f".format(childColorConsideringParents.first / onePercent).replace(',', '.').toDouble(),
//            "%.0f".format(childColorConsideringParents.second / onePercent).replace(',', '.').toDouble(),
//            "%.2f".format(childColorConsideringParents.third / onePercent).replace(',', '.').toDouble(),
//        )

        childColorConsideringParents = Triple(
            (childColorConsideringParents.first / onePercent).roundToInt().toDouble(),
            (childColorConsideringParents.second / onePercent).roundToInt().toDouble(),
            (childColorConsideringParents.third / onePercent).roundToInt().toDouble(),
        )

        val newThirdValue =
            100 - childColorConsideringParents.first - childColorConsideringParents.second
        childColorConsideringParents = Triple(
            childColorConsideringParents.first,
            childColorConsideringParents.second,
            newThirdValue
        )


        displayChildColor(childColorConsideringParents)
    }

    private fun proportionForHeir(parentsColor: Pair<String, String>): Triple<Double, Double, Double> {
        val personColor = when (parentsColor) {
            BROWN to BROWN -> Triple(75.0, 6.0, 19.0)
            GREEN to BROWN -> Triple(50.0, 13.0, 37.0)
            BROWN to GREEN -> Triple(50.0, 13.0, 37.0)
            GREY to BROWN -> Triple(49.0, 49.0, 2.0)
            BROWN to GREY -> Triple(49.0, 49.0, 2.0)
            GREEN to GREEN -> Triple(2.0, 24.0, 74.0)
            GREEN to GREY -> Triple(2.0, 49.0, 49.0)
            GREY to GREEN -> Triple(2.0, 49.0, 49.0)
            GREY to GREY -> Triple(1.0, 97.0, 2.0)
            NOT_SELECTED to BROWN -> Triple(25.0, 0.0, 0.0)
            NOT_SELECTED to GREY -> Triple(0.0, 25.0, 0.0)
            NOT_SELECTED to GREEN -> Triple(0.0, 0.0, 25.0)
            BROWN to NOT_SELECTED -> Triple(25.0, 0.0, 0.0)
            GREY to NOT_SELECTED -> Triple(0.0, 25.0, 0.0)
            GREEN to NOT_SELECTED -> Triple(0.0, 0.0, 25.0)
            else -> Triple(0.0, 0.0, 0.0)
        }
        return personColor
    }

    private fun displayChildColor(childColor: Triple<Double, Double, Double>) {

        //hideChildColorsCards()

        unHideSegmentChild()
        unHideSegmentGrandparents()

        binding.eyeCardChildBrown.visibility = View.VISIBLE
        binding.eyeCardChildGrey.visibility = View.VISIBLE
        binding.eyeCardChildGreen.visibility = View.VISIBLE

        binding.eyeMessageBrown.text = "${childColor.first} %"
        binding.eyeProgressBarBrown.setProgress(childColor.first.toInt(), true)
        binding.eyeMessageGrey.text = "${childColor.second} %"
        binding.eyeProgressBarGrey.setProgress(childColor.second.toInt(), true)
        binding.eyeMessageGreen.text = "${childColor.third} %"
        binding.eyeProgressBarGreen.setProgress(childColor.third.toInt(), true)


//        if (childColor.first != 0.0) {
//            binding.eyeCardChildBrown.visibility = View.VISIBLE
//            binding.eyeMessageBrown.visibility = View.VISIBLE
//            binding.eyeProgressBarBrown.visibility = View.VISIBLE
//        }
//        if (childColor.second != 0.0) {
//            binding.eyeCardChildGrey.visibility = View.VISIBLE
//            binding.eyeMessageGrey.visibility = View.VISIBLE
//            binding.eyeProgressBarGrey.visibility = View.VISIBLE
//        }
//        if (childColor.third != 0.0) {
//            binding.eyeCardChildGreen.visibility = View.VISIBLE
//            binding.eyeMessageGreen.visibility = View.VISIBLE
//            binding.eyeProgressBarGreen.visibility = View.VISIBLE
//        }
    }


    private fun hideSegmentChild() {
        // segment 'child'
        binding.eyeMaterialDivider1.visibility = View.GONE
        binding.eyeMaterialDivider2.visibility = View.GONE
        binding.eyeMaterialDivider3.visibility = View.GONE
        binding.eyeMaterialDivider4.visibility = View.GONE
        binding.eyeMessageChild.visibility = View.GONE
        binding.eyeCardChildBrown.visibility = View.GONE
        binding.eyeCardChildGrey.visibility = View.GONE
        binding.eyeCardChildGreen.visibility = View.GONE

        binding.eyeIvMotherQuestion.visibility = View.GONE
        binding.eyeIvFatherQuestion.visibility = View.GONE

        binding.eyeCardInfo1.visibility = View.GONE
        binding.eyeBtnAddGrandparents.visibility = View.GONE
    }

    private fun hideSegmentGrandparents() {
        // segment 'grandparents'
        binding.eyeGroupGrandparents.visibility = View.GONE
//        binding.eyeSubtitleMessageMotherGranny.visibility = View.GONE
//        binding.eyeCardMotherGranny.visibility = View.GONE
//        binding.eyeSubtitleMessageMotherGrandpa.visibility = View.GONE
//        binding.eyeCardMotherGrandpa.visibility = View.GONE
//        binding.eyeSubtitleMessageFatherGranny.visibility = View.GONE
//        binding.eyeCardFatherGranny.visibility = View.GONE
//        binding.eyeSubtitleMessageFatherGrandpa.visibility = View.GONE
//        binding.eyeCardFatherGrandpa.visibility = View.GONE
//        binding.eyeMaterialDividerF1.visibility = View.GONE
//        binding.eyeMaterialDividerF2.visibility = View.GONE
//        binding.eyeMaterialDividerF3.visibility = View.GONE
//        binding.eyeMaterialDividerF4.visibility = View.GONE
//        binding.eyeMaterialDividerF5.visibility = View.GONE
//        binding.eyeMaterialDividerF6.visibility = View.GONE
//        binding.eyeMaterialDividerF7.visibility = View.GONE
//        binding.eyeMaterialDividerF8.visibility = View.GONE
//        binding.eyeMaterialDividerF9.visibility = View.GONE
//        binding.eyeMaterialDividerF10.visibility = View.GONE
    }


    private fun hideChildColorsCards() {
        binding.eyeCardChildBrown.visibility = View.GONE
        binding.eyeCardChildGrey.visibility = View.GONE
        binding.eyeCardChildGreen.visibility = View.GONE
//        binding.eyeMessageBrown.visibility = View.GONE
//        binding.eyeMessageGrey.visibility = View.GONE
//        binding.eyeMessageGreen.visibility = View.GONE
//        binding.eyeProgressBarBrown.visibility = View.GONE
//        binding.eyeProgressBarGrey.visibility = View.GONE
//        binding.eyeProgressBarGreen.visibility = View.GONE
    }


    private fun unHideSegmentChild() {
        // segment 'child'
        binding.eyeMaterialDivider1.visibility = View.VISIBLE
        binding.eyeMaterialDivider2.visibility = View.VISIBLE
        binding.eyeMaterialDivider3.visibility = View.VISIBLE
        binding.eyeMaterialDivider4.visibility = View.VISIBLE
        binding.eyeMessageChild.visibility = View.VISIBLE
        binding.eyeCardChildBrown.visibility = View.VISIBLE
        binding.eyeCardChildGrey.visibility = View.VISIBLE
        binding.eyeCardChildGreen.visibility = View.VISIBLE

        //binding.eyeCardExplainFolded.visibility = View.VISIBLE

    }

    private fun unHideSegmentGrandparents() {
        // segment 'grandparents'
        binding.eyeGroupGrandparents.visibility = View.VISIBLE
//        binding.eyeSubtitleMessageMotherGranny.visibility = View.VISIBLE
//        binding.eyeCardMotherGranny.visibility = View.VISIBLE
//        binding.eyeSubtitleMessageMotherGrandpa.visibility = View.VISIBLE
//        binding.eyeCardMotherGrandpa.visibility = View.VISIBLE
//        binding.eyeSubtitleMessageFatherGranny.visibility = View.VISIBLE
//        binding.eyeCardFatherGranny.visibility = View.VISIBLE
//        binding.eyeSubtitleMessageFatherGrandpa.visibility = View.VISIBLE
//        binding.eyeCardFatherGrandpa.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF1.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF2.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF3.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF4.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF5.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF6.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF7.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF8.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF9.visibility = View.VISIBLE
//        binding.eyeMaterialDividerF10.visibility = View.VISIBLE
    }

    private fun changeCardHint(textView: TextView, eyeColor: String) {
        when (eyeColor) {
            BROWN -> textView.text = getString(R.string.brown)
            GREY -> textView.text = getString(R.string.blue_grey)
            GREEN -> textView.text = getString(R.string.green)
        }
    }

    private fun scrollUp() {
        binding.nestedMain.fling(-5000);
    }

    private fun scrollDown() {
        binding.nestedMain.fling(5000);
    }

}