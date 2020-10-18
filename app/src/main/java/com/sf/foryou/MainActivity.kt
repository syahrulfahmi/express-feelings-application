package com.sf.foryou

import android.animation.Animator
import android.graphics.Point
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.sf.foryou.dialog.*
import com.sf.foryou.model.UserModel
import com.sf.foryou.preference.UserPreference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), InputNameDialogFragment.DialogCallback,
    PopUpDialogFragment.FirstDialogCallBack, PopUpDialogFragment2.DialogFragment2,
    PopUpDialogFragment3.DialogFragment3, PopUpDialogFragment4.DialogFragment4,
    PopUpDialogFragment5.DialogFragment5, PopUpDialogFragment6.DialogFragment6,
    PopUpDialogFragment7.DialogFragment7, PopUpDialogFragment7Yes.DialogFragment7Yes,
    PopUpDialogFragment7ChildYes.DialogFragment7Child, PopUpDialogFragment8.DialogFragment8,
    PopUpDialogFragment9.DialogFragment9, PopUpDialogFragment10.DialogFragment10,
    PopUpDialogFragment10Yes.DialogFragment10Yes {

    private lateinit var userModel: UserModel
    private lateinit var userPreference: UserPreference

    private var name: String = ""
    private var dialogWidthSize = 0
    private var dialogIsFinish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreference = UserPreference(this)
        userModel = UserModel("")

        setUpSizeDialog()
        showDialogInputName()
        initDialog()
        initAnimation()
    }

    private fun setUpSizeDialog() {
        val point = Point()
        val display = windowManager.defaultDisplay
        display.getSize(point)

        val width = point.x
        dialogWidthSize = (width / 1.2).toInt()
    }

    private fun showDialogInputName() {
        Handler().postDelayed({
            val fragment = InputNameDialogFragment.newInstance(dialogWidthSize)
            fragment.isCancelable = false
            fragment.show(supportFragmentManager, fragment.tag)
        }, 500)
    }

    private fun initDialog() {
        btnTry.setOnClickListener {
            getDataFromReference()
            val firstName = name.split(" ")
            if (firstName.size > 1) {
                textQuotes.text = getString(R.string.home_text_quotes_end).replace("%s", firstName[0])
            } else {
                textQuotes.text = getString(R.string.home_text_quotes_end).replace("%s", name)
            }
            val fragment = PopUpDialogFragment.newInstance(dialogWidthSize, name)
            fragment.isCancelable = false
            fragment.show(supportFragmentManager, fragment.tag)
        }

        circlePink.setOnClickListener {
            circlePink.visibility = View.INVISIBLE
            circlePink2.visibility = View.VISIBLE
            textViewHere.visibility = View.VISIBLE
        }

        circlePink2.setOnClickListener {
            circlePink2.visibility = View.INVISIBLE
            circlePink3.visibility = View.VISIBLE
            textViewHere.visibility = View.INVISIBLE
            textViewHere2.visibility = View.VISIBLE
        }

        circlePink3.setOnClickListener {
            showTransition()
        }
    }

    override fun onRetreiveText(name: String) {
        btnTry.alpha = 1F
        saveToPreference(name)
    }

    private fun saveToPreference(name: String) {
        userModel.userName = name
        userPreference.setUserPreferenceData(userModel)
    }

    private fun getDataFromReference() {
        userModel = userPreference.getString()
        name = userModel.userName
    }

    private fun initAnimation() {
        var isAnimationEndOfRepeat: Boolean
        animation_view.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {
                isAnimationEndOfRepeat = true
                if (isAnimationEndOfRepeat && dialogIsFinish) {
                    textQuotes.animate()
                        .alpha(1F)
                        .setInterpolator(AccelerateInterpolator()).duration = 1000
                }
            }
        })
    }

    private fun showTransition() {
        viewBlack.apply {
            animate().alpha(1F).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {}
                override fun onAnimationEnd(p0: Animator?) {
                    dialogIsFinish = true
                    animate().alpha(0F).duration = 1000
                    circlePink3.animate().alpha(0F).duration = 300
                    textViewHere2.animate().alpha(0F).duration = 300
                    Handler().postDelayed({
                        animation_view.alpha = 1F
                    }, 300)
                }

                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationRepeat(p0: Animator?) {}

            }).duration = 1000
        }
        btnTry.animate().alpha(0F).setInterpolator(AccelerateInterpolator()).setDuration(500)
            .start()

    }

    override fun showSecondDialog() {
        val fragment1 = PopUpDialogFragment2.newInstance(dialogWidthSize, name)
        fragment1.isCancelable = false
        fragment1.show(supportFragmentManager, fragment1.tag)
    }

    override fun showDialog3() {
        val fragment1 = PopUpDialogFragment3.newInstance(dialogWidthSize, name)
        fragment1.isCancelable = false
        fragment1.show(supportFragmentManager, fragment1.tag)
    }

    override fun showDialog4() {
        val fragment = PopUpDialogFragment4.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog5() {
        val fragment = PopUpDialogFragment5.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog6() {
        val fragment = PopUpDialogFragment6.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog7() {
        val fragment = PopUpDialogFragment7.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog7Yes() {
        val fragment = PopUpDialogFragment7Yes.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog7ChildYes() {
        val fragment = PopUpDialogFragment7ChildYes.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog8() {
        val fragment = PopUpDialogFragment8.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog9() {
        val fragment = PopUpDialogFragment9.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog10() {
        val fragment = PopUpDialogFragment10.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun showDialog10Yes() {
        val fragment = PopUpDialogFragment10Yes.newInstance(dialogWidthSize, name)
        fragment.isCancelable = false
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun finishDialog() {
        circlePink.visibility = View.VISIBLE
        btnTry.visibility = View.INVISIBLE
    }
}