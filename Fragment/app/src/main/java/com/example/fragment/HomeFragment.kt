package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory: Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btn_category) {
            val mCategoryFragment = CategoryFragment()
            val mFragmentManager = parentFragmentManager
//            mFragmentManager.beginTransaction().apply {
//                replace(
//                    R.id.frame_container,
//                    mCategoryFragment,
//                    CategoryFragment::class.java.simpleName
//                )
//                addToBackStack(null)
//                commit()
//            }
            mFragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.frame_container,
                    mCategoryFragment,
                    CategoryFragment::class.java.simpleName
                )
            }
        }
    }
}