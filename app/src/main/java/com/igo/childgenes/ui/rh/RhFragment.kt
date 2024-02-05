package com.igo.childgenes.ui.rh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentRhBinding
import com.igo.childgenes.ui.*



class RhFragment : Fragment() {
    companion object {
        var rhMother = NOT_SELECTED
        var rhFather = NOT_SELECTED
        var rhMotherGranny = NOT_SELECTED
        var rhMotherGrandpa = NOT_SELECTED
        var rhFatherGranny = NOT_SELECTED
        var rhFatherGrandpa = NOT_SELECTED

        data class Quadruple<out A, out B, out C, out D>(
            val first: A,
            val second: B,
            val third: C,
            val fourth: D
        )
    }

    private var _binding: FragmentRhBinding? = null

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
        val notificationsViewModel =
            ViewModelProvider(this).get(RhViewModel::class.java)

        _binding = FragmentRhBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Настройка тулбара
//        val toolbar: Toolbar = binding.toolbar
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)



//        margins for toolbar
        val rhCardView = binding.rhToolbarCv
        val layoutParams = rhCardView.layoutParams as CollapsingToolbarLayout.LayoutParams
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val horizontalMargin = (screenWidth * 0.05).toInt() // 5% с обеих сторон
        layoutParams.width = (screenWidth - 2 * horizontalMargin).toInt()
        layoutParams.marginStart = horizontalMargin
        layoutParams.marginEnd = horizontalMargin
        rhCardView.layoutParams = layoutParams

//      cv disappears when collapsed
        val cardView = binding.rhToolbarCv
        val appBarLayout = binding.rhAppbar
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            val percentage = Math.abs(verticalOffset).toFloat() / totalScrollRange.toFloat()
            // Здесь вы можете определить, когда начать скрывать CardView
            if (percentage >= 0.1) {
                // Начните анимацию исчезновения CardView
                cardView.animate().alpha(0f).setDuration(700).start()
            } else {
                // Начните анимацию появления CardView
                cardView.animate().alpha(1f).setDuration(700).start()
            }
        })

//        imageView.alpha = 1.0f
//
//        val appBarLayout = binding.rhAppbar
//        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            val totalScrollRange = appBarLayout.totalScrollRange
//            val percentage = Math.abs(verticalOffset).toFloat() / totalScrollRange.toFloat()
//
//            // Здесь вы можете определить, когда начать скрывать CardView
//            if (percentage <= 0.3) {
//                // Начните анимацию исчезновения CardView
//                //cardView.animate().alpha(0f).setDuration(300).start()
//            } else if (percentage >= 0.1) {
//                // Начните анимацию исчезновения изображения
//                imageView.animate().alpha(0f).setDuration(700).start()
//            } else {
//                // Начните анимацию появления CardView и изображения
//                cardView.animate().alpha(1f).setDuration(600).start()
//                imageView.animate().alpha(1f).setDuration(600).start()
//            }
//        })




        return root
    }

    override fun onResume() {
        super.onResume()
        //hideSegmentGrandparents()
        binding.rhBtnAddGrandparents.visibility = View.GONE
        //hideSegmentChild()

        if (rhMother == NOT_SELECTED || rhFather == NOT_SELECTED) {
            hideSegmentChild()
        }
        fillCardMother()
        fillCardFather()
        checkChildRenderNeeded()
//        fillCardMotherGranny()
//        fillCardMotherGrandpa()
//        fillCardFatherGranny()
//        fillCardFatherGrandpa()
//        checkBtnGrandHide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toast.makeText(requireContext(), "I am here!!!", Toast.LENGTH_SHORT).show()
        //hideSegmentGrandparents()
        //hideSegmentChild()

        //checkChildRenderNeeded()

        binding.rhCardMother.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_rh_to_select_rh_fragment,
                bundleOf(PARENT_PERSON to MOTHER)
            )
        }
        binding.rhCardFather.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_rh_to_select_rh_fragment,
                bundleOf(PARENT_PERSON to FATHER)
            )
        }

        binding.rhInfo1Btn.setOnClickListener {
            if (binding.rhInfo1ArrowUp.visibility == View.INVISIBLE) {
                binding.rhInfo1Content.visibility = View.VISIBLE
                binding.rhInfo1ArrowDown.visibility = View.INVISIBLE
                binding.rhInfo1ArrowUp.visibility = View.VISIBLE
            } else {
                binding.rhInfo1Content.visibility = View.GONE
                binding.rhInfo1ArrowUp.visibility = View.INVISIBLE
                binding.rhInfo1ArrowDown.visibility = View.VISIBLE
            }
        }

//        binding.rhBtnAddGrandparents.setOnClickListener {
//            scrollUp()
//        }
//
//


//        binding.rhCardMotherGranny.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_rh_to_selectrhFragment,
//                bundleOf(PARENT_PERSON to MOTHER_GRANNY)
//            )
//        }
//        binding.rhCardMotherGrandpa.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_rh_to_selectrhFragment,
//                bundleOf(PARENT_PERSON to MOTHER_GRANDPA)
//            )
//        }
//        binding.rhCardFatherGranny.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_rh_to_selectrhFragment,
//                bundleOf(PARENT_PERSON to FATHER_GRANNY)
//            )
//        }
//
//        binding.rhCardFatherGrandpa.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_rh_to_selectrhFragment,
//                bundleOf(PARENT_PERSON to FATHER_GRANDPA)
//            )
//        }


        // getting data when return from selectFragment
        parentFragmentManager.setFragmentResultListener(RESPOND, viewLifecycleOwner) { _, data ->
            val parentPerson = data.getString(PARENT_PERSON).toString()
            val rhColor = data.getString(RH_TYPE).toString()

            when (parentPerson) {
                MOTHER -> {
                    rhMother = rhColor
                    fillCardMother()
                }

                FATHER -> {
                    rhFather = rhColor
                    fillCardFather()
                }

                MOTHER_GRANNY -> {
                    rhMotherGranny = rhColor
                    fillCardMotherGranny()
                }

                MOTHER_GRANDPA -> {
                    rhMotherGrandpa = rhColor
                    fillCardMotherGrandpa()
                }

                FATHER_GRANNY -> {
                    rhFatherGranny = rhColor
                    fillCardFatherGranny()
                }

                FATHER_GRANDPA -> {
                    rhFatherGrandpa = rhColor
                    fillCardFatherGrandpa()
                }
            }
            checkChildRenderNeeded()
        }
    }

    private fun fillCardMother() {
        if (rhMother != NOT_SELECTED) {
            binding.rhIvMotherGradient1.visibility = View.GONE
            binding.rhIvMotherGradient2.visibility = View.GONE
            changeCardHint(binding.rhMotherIconHint, rhMother)
            when (rhMother) {
                RH_PLUS -> binding.rhIvMotherGradient1.visibility = View.VISIBLE
                RH_MINUS -> binding.rhIvMotherGradient2.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFather() {
        if (rhFather != NOT_SELECTED) {
            binding.rhIvFatherGradient1.visibility = View.GONE
            binding.rhIvFatherGradient2.visibility = View.GONE
            changeCardHint(binding.rhFatherIconHint, rhFather)
            when (rhFather) {
                RH_PLUS -> binding.rhIvFatherGradient1.visibility = View.VISIBLE
                RH_MINUS -> binding.rhIvFatherGradient2.visibility = View.VISIBLE
            }
        }
    }

        private fun fillCardMotherGranny() {
        if (rhMotherGranny != NOT_SELECTED) {
            binding.rhIvMotherGrannyGradient1.visibility = View.GONE
            binding.rhIvMotherGrannyGradient2.visibility = View.GONE
            changeCardHint(binding.rhMotherGrannyIconHint, rhMotherGranny)
            when (rhMotherGranny) {
                RH_PLUS -> binding.rhIvMotherGrannyGradient1.visibility = View.VISIBLE
                RH_MINUS -> binding.rhIvMotherGrannyGradient2.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardMotherGrandpa() {
        if (rhMotherGrandpa != NOT_SELECTED) {
            binding.rhIvMotherGrandpaGradient1.visibility = View.GONE
            binding.rhIvMotherGrandpaGradient2.visibility = View.GONE
            changeCardHint(binding.rhMotherGrandpaIconHint, rhMotherGrandpa)
            when (rhMotherGrandpa) {
                RH_PLUS -> binding.rhIvMotherGrandpaGradient1.visibility = View.VISIBLE
                RH_MINUS -> binding.rhIvMotherGrandpaGradient2.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFatherGranny() {
        if (rhFatherGranny != NOT_SELECTED) {
            binding.rhIvFatherGrannyGradient1.visibility = View.GONE
            binding.rhIvFatherGrannyGradient2.visibility = View.GONE
            changeCardHint(binding.rhFatherGrannyIconHint, rhFatherGranny)
            when (rhFatherGranny) {
                RH_PLUS-> binding.rhIvFatherGrannyGradient1.visibility = View.VISIBLE
                RH_MINUS -> binding.rhIvFatherGrannyGradient2.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFatherGrandpa() {
        if (rhFatherGrandpa != NOT_SELECTED) {
            binding.rhIvFatherGrandpaGradient1.visibility = View.GONE
            binding.rhIvFatherGrandpaGradient2.visibility = View.GONE
            changeCardHint(binding.rhFatherGrandpaIconHint, rhFatherGrandpa)
            when (rhFatherGrandpa) {
                BROWN -> binding.rhIvFatherGrandpaGradient1.visibility = View.VISIBLE
                GREY -> binding.rhIvFatherGrandpaGradient2.visibility = View.VISIBLE
            }
        }
    }
    
    private fun checkChildRenderNeeded() {
        if (rhMother != NOT_SELECTED && rhFather != NOT_SELECTED) {
            countColor()
            scrollDown()
        }
    }

    private fun checkBtnGrandHide() {
        if ((rhMother != NOT_SELECTED && rhFather != NOT_SELECTED) &&
            (rhMotherGranny == NOT_SELECTED || rhMotherGrandpa == NOT_SELECTED ||
                    rhFatherGranny == NOT_SELECTED || rhFatherGranny == NOT_SELECTED
                    )
        ) {
            binding.rhBtnAddGrandparents.visibility = View.VISIBLE
        } else {
            binding.rhBtnAddGrandparents.visibility = View.GONE
        }
    }


    private fun countColor() {

        // if (parentsColor == com.igo.childgenes.ui.BROWN to com.igo.childgenes.ui.BROWN) {val sfdad: Int = 23}
        //Toast.makeText(requireContext(), "Colors: $parentsColor", Toast.LENGTH_SHORT).show()

        val childColor = proportionForHeir(
            Pair(
                rhMother,
                rhFather
            )
        )
        displayChildColor(childColor)
    }

    private fun proportionForHeir(parentsColor: Pair<String, String>): Companion.Quadruple<Double, Double, Double, Double> {
        val personColor: Companion.Quadruple<Double, Double, Double, Double> = when (parentsColor) {
            RH_PLUS to RH_PLUS -> Companion.Quadruple(
                100.0,
                0.0,
                0.0,
                0.0
            )

            RH_MINUS to RH_PLUS -> Companion.Quadruple(
                25.0,
                75.0,
                0.0,
                0.0
            )

            RH_PLUS to RH_MINUS -> Companion.Quadruple(
                25.0,
                75.0,
                0.0,
                0.0
            )

            RH_MINUS to RH_MINUS -> Companion.Quadruple(
                6.25,
                93.75,
                0.0,
                0.0
            )

            else -> Companion.Quadruple(0.0, 0.0, 0.0, 0.0)
        }
        return personColor
    }

    private fun displayChildColor(childColor: Companion.Quadruple<Double, Double, Double, Double>) {

        //hideChildColorsCards()

        unHideSegmentChild()

        binding.rhCardChild1.visibility = View.VISIBLE
        binding.rhCardChild2.visibility = View.VISIBLE

        binding.rhMessage1.text = "${childColor.first} %"
        binding.rhProgressBar1.setProgress(childColor.first.toInt(), true)
        binding.rhMessage2.text = "${childColor.second} %"
        binding.rhProgressBar2.setProgress(childColor.second.toInt(), true)
    }

    private fun hideSegmentChild() {
        binding.rhMaterialDivider1.visibility = View.GONE
        binding.rhMaterialDivider2.visibility = View.GONE
        binding.rhMaterialDivider3.visibility = View.GONE
        binding.rhMaterialDivider4.visibility = View.GONE
        binding.rhMessageChild.visibility = View.GONE
        binding.rhCardChild1.visibility = View.GONE
        binding.rhCardChild2.visibility = View.GONE
        binding.rhCardInfo1.visibility = View.GONE
        //binding.rhBtnAddGrandparents.visibility = View.GONE
    }

    private fun unHideSegmentChild() {
        // segment 'child'
        binding.rhMaterialDivider1.visibility = View.VISIBLE
        binding.rhMaterialDivider2.visibility = View.VISIBLE
        binding.rhMaterialDivider3.visibility = View.VISIBLE
        binding.rhMaterialDivider4.visibility = View.VISIBLE
        binding.rhMessageChild.visibility = View.VISIBLE
        binding.rhCardChild1.visibility = View.VISIBLE
        binding.rhCardChild2.visibility = View.VISIBLE

    }

    private fun scrollDown() {
        binding.rhNested.fling(5000);
    }

    private fun hideSegmentGrandparents() {
        // segment 'grandparents'
        binding.rhSubtitleMessageMotherGranny.visibility = View.GONE
        binding.rhCardMotherGranny.visibility = View.GONE
        binding.rhSubtitleMessageMotherGrandpa.visibility = View.GONE
        binding.rhCardMotherGrandpa.visibility = View.GONE
        binding.rhSubtitleMessageFatherGranny.visibility = View.GONE
        binding.rhCardFatherGranny.visibility = View.GONE
        binding.rhSubtitleMessageFatherGrandpa.visibility = View.GONE
        binding.rhCardFatherGrandpa.visibility = View.GONE
        binding.rhMaterialDividerF1.visibility = View.GONE
        binding.rhMaterialDividerF2.visibility = View.GONE
        binding.rhMaterialDividerF3.visibility = View.GONE
        binding.rhMaterialDividerF4.visibility = View.GONE
        binding.rhMaterialDividerF5.visibility = View.GONE
        binding.rhMaterialDividerF6.visibility = View.GONE
        binding.rhMaterialDividerF7.visibility = View.GONE
        binding.rhMaterialDividerF8.visibility = View.GONE
        binding.rhMaterialDividerF9.visibility = View.GONE
        binding.rhMaterialDividerF10.visibility = View.GONE
    }

    private fun changeCardHint(textView: TextView, personProperty: String) {
        when (personProperty) {
            RH_PLUS -> textView.text = getString(R.string.type_o)
            RH_MINUS -> textView.text = getString(R.string.type_a)
        }
    }

      
    

}