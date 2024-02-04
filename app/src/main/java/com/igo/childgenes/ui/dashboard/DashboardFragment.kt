package com.igo.childgenes.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.childgenes.R
import com.igo.childgenes.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    companion object {
        const val PARENT_PERSON = "PARENT_PERSON"
        const val MOTHER = "Mother"
        const val FATHER = "Father"
//        const val MOTHER_GRANNY = "MotherGranny"
//        const val MOTHER_GRANDPA = "MotherGrandpa"
//        const val FATHER_GRANNY = "FatherGranny"
//        const val FATHER_GRANDPA = "FatherGrandpa"

        const val RESPOND = "RESPOND"
        const val BLOOD_TYPE = "BLOOD_TYPE"

        const val GROUP_1 = "GROUP_1"
        const val GROUP_2 = "GROUP_2"
        const val GROUP_3 = "GROUP_3"
        const val GROUP_4 = "GROUP_4"
        const val NOT_SELECTED = "NOT_SELECTED"

        var bloodMother = NOT_SELECTED
        var bloodFather = NOT_SELECTED
//        var bloodMotherGranny = NOT_SELECTED
//        var bloodMotherGrandpa = NOT_SELECTED
//        var bloodFatherGranny = NOT_SELECTED
//        var bloodFatherGrandpa = NOT_SELECTED

        data class Quadruple<out A, out B, out C, out D>(
            val first: A,
            val second: B,
            val third: C,
            val fourth: D
        )
    }

    private var _binding: FragmentDashboardBinding? = null

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
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onResume() {
        super.onResume()
        hideSegmentGrandparents()
        binding.bloodBtnAddGrandparents.visibility = View.GONE
        //hideSegmentChild()

        if (bloodMother == NOT_SELECTED || bloodFather == NOT_SELECTED) {
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

        binding.bloodCardMother.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_dashboard_to_select_blood_fragment,
                bundleOf(PARENT_PERSON to MOTHER)
            )
        }
        binding.bloodCardFather.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_dashboard_to_select_blood_fragment,
                bundleOf(PARENT_PERSON to FATHER)
            )
        }

        binding.bloodInfo1Btn.setOnClickListener {
            if (binding.bloodInfo1ArrowUp.visibility == View.INVISIBLE) {
                binding.bloodInfo1Content.visibility = View.VISIBLE
                binding.bloodInfo1ArrowDown.visibility = View.INVISIBLE
                binding.bloodInfo1ArrowUp.visibility = View.VISIBLE
            } else {
                binding.bloodInfo1Content.visibility = View.GONE
                binding.bloodInfo1ArrowUp.visibility = View.INVISIBLE
                binding.bloodInfo1ArrowDown.visibility = View.VISIBLE
            }
        }

//        binding.bloodBtnAddGrandparents.setOnClickListener {
//            scrollUp()
//        }
//
//


//        binding.bloodCardMotherGranny.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_dashboard_to_selectBloodFragment,
//                bundleOf(PARENT_PERSON to MOTHER_GRANNY)
//            )
//        }
//        binding.bloodCardMotherGrandpa.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_dashboard_to_selectBloodFragment,
//                bundleOf(PARENT_PERSON to MOTHER_GRANDPA)
//            )
//        }
//        binding.bloodCardFatherGranny.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_dashboard_to_selectBloodFragment,
//                bundleOf(PARENT_PERSON to FATHER_GRANNY)
//            )
//        }
//
//        binding.bloodCardFatherGrandpa.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_navigation_dashboard_to_selectBloodFragment,
//                bundleOf(PARENT_PERSON to FATHER_GRANDPA)
//            )
//        }


        // getting data when return from selectFragment
        parentFragmentManager.setFragmentResultListener(RESPOND, viewLifecycleOwner) { _, data ->
            val parentPerson = data.getString(PARENT_PERSON).toString()
            val bloodColor = data.getString(BLOOD_TYPE).toString()

            when (parentPerson) {
                MOTHER -> {
                    bloodMother = bloodColor
                    fillCardMother()
                }

                FATHER -> {
                    bloodFather = bloodColor
                    fillCardFather()
                }

//                MOTHER_GRANNY -> {
//                    bloodMotherGranny = bloodColor
//                    fillCardMotherGranny()
//                }
//
//                MOTHER_GRANDPA -> {
//                    bloodMotherGrandpa = bloodColor
//                    fillCardMotherGrandpa()
//                }
//
//                FATHER_GRANNY -> {
//                    bloodFatherGranny = bloodColor
//                    fillCardFatherGranny()
//                }
//
//                FATHER_GRANDPA -> {
//                    bloodFatherGrandpa = bloodColor
//                    fillCardFatherGrandpa()
//                }
            }
            checkChildRenderNeeded()
        }
    }

    private fun fillCardMother() {
        if (bloodMother != NOT_SELECTED) {
            binding.bloodIvMotherGradient1.visibility = View.GONE
            binding.bloodIvMotherGradient2.visibility = View.GONE
            binding.bloodIvMotherGradient3.visibility = View.GONE
            binding.bloodIvMotherGradient4.visibility = View.GONE
            changeCardHint(binding.bloodMotherIconHint, bloodMother)
            when (bloodMother) {
                GROUP_1 -> binding.bloodIvMotherGradient1.visibility = View.VISIBLE
                GROUP_2 -> binding.bloodIvMotherGradient2.visibility = View.VISIBLE
                GROUP_3 -> binding.bloodIvMotherGradient3.visibility = View.VISIBLE
                GROUP_4 -> binding.bloodIvMotherGradient4.visibility = View.VISIBLE
            }
        }
    }

    private fun fillCardFather() {
        if (bloodFather != NOT_SELECTED) {
            binding.bloodIvFatherGradient1.visibility = View.GONE
            binding.bloodIvFatherGradient2.visibility = View.GONE
            binding.bloodIvFatherGradient3.visibility = View.GONE
            binding.bloodIvFatherGradient4.visibility = View.GONE
            changeCardHint(binding.bloodFatherIconHint, bloodFather)
            when (bloodFather) {
                GROUP_1 -> binding.bloodIvFatherGradient1.visibility = View.VISIBLE
                GROUP_2 -> binding.bloodIvFatherGradient2.visibility = View.VISIBLE
                GROUP_3 -> binding.bloodIvFatherGradient3.visibility = View.VISIBLE
                GROUP_4 -> binding.bloodIvFatherGradient4.visibility = View.VISIBLE
            }
        }
    }

    private fun checkChildRenderNeeded() {
        if (bloodMother != NOT_SELECTED && bloodFather != NOT_SELECTED) {
            countColor()
            scrollDown()
        }
    }

    private fun countColor() {

        // if (parentsColor == com.igo.childgenes.ui.BROWN to com.igo.childgenes.ui.BROWN) {val sfdad: Int = 23}
        //Toast.makeText(requireContext(), "Colors: $parentsColor", Toast.LENGTH_SHORT).show()

        val childColor = proportionForHeir(Pair(bloodMother, bloodFather))
        displayChildColor(childColor)
    }

    private fun proportionForHeir(parentsColor: Pair<String, String>): Quadruple<Double, Double, Double, Double> {
        val personColor: Quadruple<Double, Double, Double, Double> = when (parentsColor) {
            GROUP_1 to GROUP_1 -> Quadruple(100.0, 0.0, 0.0, 0.0)
            GROUP_3 to GROUP_1 -> Quadruple(25.0, 0.0, 75.0, 0.0)
            GROUP_1 to GROUP_3 -> Quadruple(25.0, 0.0, 75.0, 0.0)
            GROUP_2 to GROUP_1 -> Quadruple(25.0, 75.0, 0.0, 0.0)
            GROUP_1 to GROUP_2 -> Quadruple(25.0, 75.0, 0.0, 0.0)
            GROUP_3 to GROUP_3 -> Quadruple(6.25, 0.0, 93.75, 0.0)
            GROUP_3 to GROUP_2 -> Quadruple(6.25, 18.75, 18.75, 56.25)
            GROUP_2 to GROUP_3 -> Quadruple(6.25, 18.75, 18.75, 56.25)
            GROUP_2 to GROUP_2 -> Quadruple(6.25, 93.75, 0.0, 0.0)
            GROUP_4 to GROUP_1 -> Quadruple(0.0, 50.0, 50.0, 0.0)
            GROUP_1 to GROUP_4 -> Quadruple(0.0, 50.0, 50.0, 0.0)
            GROUP_4 to GROUP_2 -> Quadruple(0.0, 50.0, 12.5, 37.5)
            GROUP_2 to GROUP_4 -> Quadruple(0.0, 50.0, 12.5, 37.5)
            GROUP_4 to GROUP_3 -> Quadruple(0.0, 12.5, 50.0, 37.5)
            GROUP_3 to GROUP_4 -> Quadruple(0.0, 12.5, 50.0, 37.5)
            GROUP_4 to GROUP_4 -> Quadruple(0.0, 25.0, 25.0, 50.0)
            else -> Quadruple(0.0, 0.0, 0.0, 0.0)
        }
        return personColor
    }

    private fun displayChildColor(childColor: Quadruple<Double, Double, Double, Double>) {

        //hideChildColorsCards()

        unHideSegmentChild()

        binding.bloodCardChild1.visibility = View.VISIBLE
        binding.bloodCardChild2.visibility = View.VISIBLE
        binding.bloodCardChild3.visibility = View.VISIBLE
        binding.bloodCardChild4.visibility = View.VISIBLE

        binding.bloodMessage1.text = "${childColor.first} %"
        binding.bloodProgressBar1.setProgress(childColor.first.toInt(), true)
        binding.bloodMessage2.text = "${childColor.second} %"
        binding.bloodProgressBar2.setProgress(childColor.second.toInt(), true)
        binding.bloodMessage3.text = "${childColor.third} %"
        binding.bloodProgressBar3.setProgress(childColor.third.toInt(), true)
        binding.bloodMessage4.text = "${childColor.fourth} %"
        binding.bloodProgressBar4.setProgress(childColor.fourth.toInt(), true)
    }

    private fun hideSegmentChild() {
        binding.bloodMaterialDivider1.visibility = View.GONE
        binding.bloodMaterialDivider2.visibility = View.GONE
        binding.bloodMaterialDivider3.visibility = View.GONE
        binding.bloodMaterialDivider4.visibility = View.GONE
        binding.bloodMessageChild.visibility = View.GONE
        binding.bloodCardChild1.visibility = View.GONE
        binding.bloodCardChild2.visibility = View.GONE
        binding.bloodCardChild3.visibility = View.GONE
        binding.bloodCardChild4.visibility = View.GONE
        binding.bloodCardInfo1.visibility = View.GONE
        //binding.bloodBtnAddGrandparents.visibility = View.GONE
    }

    private fun unHideSegmentChild() {
        // segment 'child'
        binding.bloodMaterialDivider1.visibility = View.VISIBLE
        binding.bloodMaterialDivider2.visibility = View.VISIBLE
        binding.bloodMaterialDivider3.visibility = View.VISIBLE
        binding.bloodMaterialDivider4.visibility = View.VISIBLE
        binding.bloodMessageChild.visibility = View.VISIBLE
        binding.bloodCardChild1.visibility = View.VISIBLE
        binding.bloodCardChild2.visibility = View.VISIBLE
        binding.bloodCardChild3.visibility = View.VISIBLE
        binding.bloodCardChild4.visibility = View.VISIBLE

        //binding.eyeCardExplainFolded.visibility = View.VISIBLE

    }

    private fun scrollDown() {
        binding.bloodNested.fling(5000);
    }

    private fun hideSegmentGrandparents() {
        // segment 'grandparents'
        binding.bloodSubtitleMessageMotherGranny.visibility = View.GONE
        binding.bloodCardMotherGranny.visibility = View.GONE
        binding.bloodSubtitleMessageMotherGrandpa.visibility = View.GONE
        binding.bloodCardMotherGrandpa.visibility = View.GONE
        binding.bloodSubtitleMessageFatherGranny.visibility = View.GONE
        binding.bloodCardFatherGranny.visibility = View.GONE
        binding.bloodSubtitleMessageFatherGrandpa.visibility = View.GONE
        binding.bloodCardFatherGrandpa.visibility = View.GONE
        binding.bloodMaterialDividerF1.visibility = View.GONE
        binding.bloodMaterialDividerF2.visibility = View.GONE
        binding.bloodMaterialDividerF3.visibility = View.GONE
        binding.bloodMaterialDividerF4.visibility = View.GONE
        binding.bloodMaterialDividerF5.visibility = View.GONE
        binding.bloodMaterialDividerF6.visibility = View.GONE
        binding.bloodMaterialDividerF7.visibility = View.GONE
        binding.bloodMaterialDividerF8.visibility = View.GONE
        binding.bloodMaterialDividerF9.visibility = View.GONE
        binding.bloodMaterialDividerF10.visibility = View.GONE
    }

    private fun changeCardHint(textView: TextView, personProperty: String) {
        when (personProperty) {
            GROUP_1 -> textView.text = getString(R.string.type_o)
            GROUP_2 -> textView.text = getString(R.string.type_a)
            GROUP_3 -> textView.text = getString(R.string.type_b)
            GROUP_4 -> textView.text = getString(R.string.type_ab)
        }
    }

}