package com.sf.foryou.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.sf.foryou.R
import kotlinx.android.synthetic.main.layout_2.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 17/10/20
 */

class PopUpDialogFragment2 : DialogFragment() {

    private var widthSize = 0
    private var name = ""
    var listener: DialogFragment2? = null

    companion object {
        fun newInstance(widthSize: Int, name: String) = PopUpDialogFragment2().apply {
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
        val view = inflater.inflate(R.layout.dialog_pop_up_2, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.findViewById<ConstraintLayout>(R.id.containerView2).layoutParams = ConstraintLayout.LayoutParams(widthSize, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = requireActivity() as DialogFragment2

        btnNext2.setOnClickListener {
            listener?.showDialog3()
            dismiss()
        }
    }

    interface DialogFragment2 {
        fun showDialog3()
    }
}