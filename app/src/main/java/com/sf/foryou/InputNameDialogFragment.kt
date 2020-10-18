package com.sf.foryou

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_input_name.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 18/10/20
 */

class InputNameDialogFragment : DialogFragment() {

    private var widthSize = 0
    var dialogCallback: DialogCallback? = null

    companion object {
        fun newInstance(widthSize: Int) = InputNameDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("WIDTH", widthSize)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            widthSize = it.getInt("WIDTH")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_input_name, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.findViewById<LinearLayoutCompat>(R.id.containerView).layoutParams =
            ConstraintLayout.LayoutParams(widthSize, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogCallback = activity as? DialogCallback

        btnSubmit.setOnClickListener {
            if (inputName.text.isNullOrEmpty()) {
                inputName.error = "Harus Diisi"
            } else {
                dialogCallback?.onRetreiveText(inputName.text.toString())
                dismiss()
            }
        }
    }

    interface DialogCallback {
        fun onRetreiveText(name: String)
    }
}