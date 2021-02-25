package com.dindinn.demo.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import androidx.fragment.app.Fragment

class IntroPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return ImageView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resId =
            arguments?.getInt(IMAGE_RES_ID_KEY) ?: throw IllegalArgumentException("image res id")
        val imageView = (view as ImageView)
        imageView.setImageResource(resId)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setBackgroundColor(Color.parseColor("#000000"))
        imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    companion object {
        const val IMAGE_RES_ID_KEY = "res_id_key"
        fun newInstance(resId: Int): IntroPageFragment {
            val args = Bundle()
            args.putInt(IMAGE_RES_ID_KEY, resId)
            val fragment = IntroPageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}