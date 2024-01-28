package com.igo.childgenes.ui.notifications

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
import com.igo.childgenes.databinding.FragmentNotificationsBinding
import com.igo.childgenes.ui.home.HomeFragment

class NotificationsFragment : Fragment() {
    companion object {
        const val PARENT_PERSON = "com.igo.childgenes.ui.PARENT_PERSON"
        const val MOTHER = "Mother"
        const val FATHER = "Father"

        const val RESPOND = "com.igo.childgenes.ui.RESPOND"
        const val EYES_COLOR = "com.igo.childgenes.ui.EYES_COLOR"

        const val BROWN = "com.igo.childgenes.ui.BROWN"
        const val GREY = "com.igo.childgenes.ui.GREY"
        const val GREEN = "com.igo.childgenes.ui.GREEN"
        const val NOT_SELECTED = "com.igo.childgenes.ui.NOT_SELECTED"

        var eyesMotherColor = NOT_SELECTED
        var eyesFatherColor = NOT_SELECTED
    }

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this).get(NotificationsViewModel::class.java)
//
//        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        return root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //Toast.makeText(requireContext(), "I am here!!!", Toast.LENGTH_SHORT).show()
//        hideSegmentGrandparents()
//        hideSegmentChild()
//        fillCardMotherColor()
//        fillCardFatherColor()
//        //checkChildRenderNeeded()
//
//        binding.eyeCardMother.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_home_to_select_eyes_fragment,
//                bundleOf(HomeFragment.com.igo.childgenes.ui.PARENT_PERSON to HomeFragment.com.igo.childgenes.ui.MOTHER)
//            )
//        }
//        binding.eyeCardFather.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_home_to_select_eyes_fragment,
//                bundleOf(HomeFragment.com.igo.childgenes.ui.PARENT_PERSON to HomeFragment.com.igo.childgenes.ui.FATHER)
//            )
//        }
//
//        // getting data when return from selectFragment
//        parentFragmentManager.setFragmentResultListener(HomeFragment.com.igo.childgenes.ui.RESPOND, viewLifecycleOwner) { _, data ->
//            val parentPerson = data.getString(HomeFragment.com.igo.childgenes.ui.PARENT_PERSON).toString()
//            val eyesColor = data.getString(HomeFragment.com.igo.childgenes.ui.EYES_COLOR).toString()
//
//            when (parentPerson) {
//                HomeFragment.com.igo.childgenes.ui.MOTHER -> {
//                    HomeFragment.eyesMotherColor = eyesColor
//                    fillCardMotherColor()
//                }
//
//                HomeFragment.com.igo.childgenes.ui.FATHER -> {
//                    HomeFragment.eyesFatherColor = eyesColor
//                    fillCardFatherColor()
//                }
//            }
//            checkChildRenderNeeded()
//        }
//
//        binding.eyeInfo1ArrowDown.setOnClickListener {
//            binding.eyeInfo1Content.visibility = View.VISIBLE
//            binding.eyeInfo1ArrowDown.visibility = View.INVISIBLE
//            binding.eyeInfo1ArrowUp.visibility = View.VISIBLE
//        }
//        binding.eyeInfo1ArrowUp.setOnClickListener {
//            binding.eyeInfo1Content.visibility = View.GONE
//            binding.eyeInfo1ArrowUp.visibility = View.INVISIBLE
//            binding.eyeInfo1ArrowDown.visibility = View.VISIBLE
//        }
//
//        binding.eyeBtnAddGrandparents.setOnClickListener {
//            scrollUp()
//        }
//    }
//
//
//    private fun fillCardMotherColor() {
//        Toast.makeText(requireContext(), "fillCardMotherColor", Toast.LENGTH_SHORT).show()
//
//        if (HomeFragment.eyesMotherColor == HomeFragment.com.igo.childgenes.ui.NOT_SELECTED) {
//            binding.eyeIvMotherQuestion.visibility = View.VISIBLE
//        } else {
//            binding.eyeIvMotherQuestion.visibility = View.GONE
//            binding.eyeIvMotherGradientBrown.visibility = View.GONE
//            binding.eyeIvMotherGradientBlue.visibility = View.GONE
//            binding.eyeIvMotherGradientGreen.visibility = View.GONE
//            when (HomeFragment.eyesMotherColor) {
//                HomeFragment.com.igo.childgenes.ui.BROWN -> binding.eyeIvMotherGradientBrown.visibility = View.VISIBLE
//                HomeFragment.com.igo.childgenes.ui.GREY -> binding.eyeIvMotherGradientBlue.visibility = View.VISIBLE
//                HomeFragment.com.igo.childgenes.ui.GREEN -> binding.eyeIvMotherGradientGreen.visibility = View.VISIBLE
//            }
//        }
//    }
//
//
//    private fun fillCardFatherColor() {
//        if (HomeFragment.eyesFatherColor == HomeFragment.com.igo.childgenes.ui.NOT_SELECTED) {
//            binding.eyeIvFatherQuestion.visibility = View.VISIBLE
//        } else {
//            binding.eyeIvFatherQuestion.visibility = View.GONE
//            binding.eyeIvFatherGradientBrown.visibility = View.GONE
//            binding.eyeIvFatherGradientBlue.visibility = View.GONE
//            binding.eyeIvFatherGradientGreen.visibility = View.GONE
//            when (HomeFragment.eyesFatherColor) {
//                HomeFragment.com.igo.childgenes.ui.BROWN -> binding.eyeIvFatherGradientBrown.visibility = View.VISIBLE
//                HomeFragment.com.igo.childgenes.ui.GREY -> binding.eyeIvFatherGradientBlue.visibility = View.VISIBLE
//                HomeFragment.com.igo.childgenes.ui.GREEN -> binding.eyeIvFatherGradientGreen.visibility = View.VISIBLE
////                    binding.cardFather.setCardBackgroundColor(
////                        ContextCompat.getColor(requireContext(), R.color.eyesColorBrown)
////                    )
////                    // Получите Drawable из ресурсов
////                    val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_eye_24)
////                    // Создайте копию Drawable с цветовой фильтрацией
////                    val coloredDrawable: Drawable? = drawable?.mutate()
////                    coloredDrawable?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
////                    // Установите фон как градиентный Drawable
////                    val gradientDrawable = resources.getDrawable(R.drawable.gradient_brown) as Drawable
////                    binding.ivFatherEye.background = gradientDrawable
////
////                    binding.ivFatherEye.setColorFilter(ContextCompat.getColor(requireContext(), R.color.eyesColorBrown))
//            }
//        }
//    }
//
//    private fun checkChildRenderNeeded() {
//        if (HomeFragment.eyesMotherColor != HomeFragment.com.igo.childgenes.ui.NOT_SELECTED && HomeFragment.eyesFatherColor != HomeFragment.com.igo.childgenes.ui.NOT_SELECTED) {
//            countColor()
//            scrollDown()
//            unHideSegmentGrandparents()
//        }
//    }
//
//    private fun countColor() {
//        val parentsColor = Pair(HomeFragment.eyesMotherColor, HomeFragment.eyesFatherColor)
//        // if (parentsColor == com.igo.childgenes.ui.BROWN to com.igo.childgenes.ui.BROWN) {val sfdad: Int = 23}
//        Toast.makeText(requireContext(), "Colors: $parentsColor", Toast.LENGTH_SHORT).show()
//
//        val childColor = when (parentsColor) {
//            HomeFragment.com.igo.childgenes.ui.BROWN to HomeFragment.com.igo.childgenes.ui.BROWN -> Triple(75.0, 6.25, 18.75)
//            HomeFragment.com.igo.childgenes.ui.GREEN to HomeFragment.com.igo.childgenes.ui.BROWN -> Triple(50.0, 12.5, 37.5)
//            HomeFragment.com.igo.childgenes.ui.BROWN to HomeFragment.com.igo.childgenes.ui.GREEN -> Triple(50.0, 12.5, 37.5)
//            HomeFragment.com.igo.childgenes.ui.GREY to HomeFragment.com.igo.childgenes.ui.BROWN -> Triple(50.0, 50.0, 0.0)
//            HomeFragment.com.igo.childgenes.ui.BROWN to HomeFragment.com.igo.childgenes.ui.GREY -> Triple(50.0, 50.0, 0.0)
//            HomeFragment.com.igo.childgenes.ui.GREEN to HomeFragment.com.igo.childgenes.ui.GREEN -> Triple(1.0, 24.5, 74.5)
//            HomeFragment.com.igo.childgenes.ui.GREEN to HomeFragment.com.igo.childgenes.ui.GREY -> Triple(0.0, 50.0, 50.0)
//            HomeFragment.com.igo.childgenes.ui.GREY to HomeFragment.com.igo.childgenes.ui.GREEN -> Triple(0.0, 50.0, 50.0)
//            HomeFragment.com.igo.childgenes.ui.GREY to HomeFragment.com.igo.childgenes.ui.GREY -> Triple(0.0, 99.0, 1.0)
//            else -> Triple(0.0, 0.0, 0.0)
//        }
//        displayChildColor(childColor)
//    }
//
//    private fun displayChildColor(childColor: Triple<Double, Double, Double>) {
//
//        hideChildColorsCards()
//
//        unHideSegmentChildExceptColorsCards()
//        binding.eyeCardChildBrown.visibility = View.VISIBLE
//        binding.eyeCardChildGrey.visibility = View.VISIBLE
//        binding.eyeCardChildGreen.visibility = View.VISIBLE
//
//        binding.eyeMessageBrown.text = "${childColor.first} %"
//        binding.eyeProgressBarBrown.setProgress(childColor.first.toInt(), true)
//        binding.eyeMessageGrey.text = "${childColor.second} %"
//        binding.eyeProgressBarGrey.setProgress(childColor.second.toInt(), true)
//        binding.eyeMessageGreen.text = "${childColor.third} %"
//        binding.eyeProgressBarGreen.setProgress(childColor.third.toInt(), true)
//
//
//
////        if (childColor.first != 0.0) {
////            binding.eyeCardChildBrown.visibility = View.VISIBLE
////            binding.eyeMessageBrown.visibility = View.VISIBLE
////            binding.eyeProgressBarBrown.visibility = View.VISIBLE
////        }
////        if (childColor.second != 0.0) {
////            binding.eyeCardChildGrey.visibility = View.VISIBLE
////            binding.eyeMessageGrey.visibility = View.VISIBLE
////            binding.eyeProgressBarGrey.visibility = View.VISIBLE
////        }
////        if (childColor.third != 0.0) {
////            binding.eyeCardChildGreen.visibility = View.VISIBLE
////            binding.eyeMessageGreen.visibility = View.VISIBLE
////            binding.eyeProgressBarGreen.visibility = View.VISIBLE
////        }
//    }
//
//
//
//
//    private fun hideSegmentChild() {
//        // segment 'child'
//        binding.eyeMaterialDivider1.visibility = View.GONE
//        binding.eyeMaterialDivider2.visibility = View.GONE
//        binding.eyeMaterialDivider3.visibility = View.GONE
//        binding.eyeMaterialDivider4.visibility = View.GONE
//        binding.eyeMessageChild.visibility = View.GONE
//        binding.eyeCardChildBrown.visibility = View.GONE
//        binding.eyeCardChildGrey.visibility = View.GONE
//        binding.eyeCardChildGreen.visibility = View.GONE
//    }
//
//    private fun hideSegmentGrandparents() {
//        // segment 'grandparents'
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
//    }
//
//
//    private fun hideChildColorsCards() {
//        binding.eyeCardChildBrown.visibility = View.GONE
//        binding.eyeCardChildGrey.visibility = View.GONE
//        binding.eyeCardChildGreen.visibility = View.GONE
////        binding.eyeMessageBrown.visibility = View.GONE
////        binding.eyeMessageGrey.visibility = View.GONE
////        binding.eyeMessageGreen.visibility = View.GONE
////        binding.eyeProgressBarBrown.visibility = View.GONE
////        binding.eyeProgressBarGrey.visibility = View.GONE
////        binding.eyeProgressBarGreen.visibility = View.GONE
//    }
//
//
//    private fun unHideSegmentChildExceptColorsCards() {
//        // segment 'child'
//        binding.eyeMaterialDivider1.visibility = View.VISIBLE
//        binding.eyeMaterialDivider2.visibility = View.VISIBLE
//        binding.eyeMaterialDivider3.visibility = View.VISIBLE
//        binding.eyeMaterialDivider4.visibility = View.VISIBLE
//        binding.eyeMessageChild.visibility = View.VISIBLE
////        binding.eyeCardChildBrown.visibility = View.VISIBLE
////        binding.eyeCardChildGrey.visibility = View.VISIBLE
////        binding.eyeCardChildGreen.visibility = View.VISIBLE
//
//        //binding.eyeCardExplainFolded.visibility = View.VISIBLE
//
//    }
//
//    private fun unHideSegmentGrandparents() {
//        // segment 'grandparents'
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
//    }
//
//    private fun scrollUp() {
//        binding.nestedMain.fling(-5000);
//    }
//
//    private fun scrollDown() {
//        binding.nestedMain.fling(5000);
//    }

}