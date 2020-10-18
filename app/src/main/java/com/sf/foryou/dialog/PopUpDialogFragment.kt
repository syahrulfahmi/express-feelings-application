package com.sf.foryou.dialog

import android.animation.Animator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.sf.foryou.R
import kotlinx.android.synthetic.main.layout_1.*
import kotlinx.android.synthetic.main.layout_1.btnNext

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 17/10/20
 */

class PopUpDialogFragment : DialogFragment() {

    private var widthSize = 0
    private var name = ""

    var firstDialogCallBack: FirstDialogCallBack? = null

    companion object {
        fun newInstance(widthSize: Int, name: String) = PopUpDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("WIDTH", widthSize)
                putString("NAME", name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            widthSize = it.getInt("WIDTH")
            name = it.getString("NAME") ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_pop_up, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.findViewById<ConstraintLayout>(R.id.containerView).layoutParams = ConstraintLayout.LayoutParams(widthSize, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstDialogCallBack = requireActivity() as FirstDialogCallBack

        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animationView.visibility = View.INVISIBLE

                val fadeIn = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator() //add this
                fadeIn.duration = 1000

                val animation = AnimationSet(false) //change to false
                animation.addAnimation(fadeIn)
                initFirstView(animation)
            }
        })
    }

    private fun initFirstView(anim: Animation) {
        textViewHi.apply {
            text = getString(R.string.home_halo).replace("%s", name)
            visibility = View.VISIBLE
            this.animation = anim
        }
        btnNext.apply {
            visibility = View.VISIBLE
            this.animation = anim
            setOnClickListener {
                firstDialogCallBack?.showSecondDialog()
                dismiss()
            }
        }
    }

    interface FirstDialogCallBack {
        fun showSecondDialog()
    }
}