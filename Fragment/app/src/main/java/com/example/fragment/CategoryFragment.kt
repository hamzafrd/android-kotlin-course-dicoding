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
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory = view.findViewById<Button>(R.id.btn_detail_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btn_detail_category) {
            val mDetailCategoryFragment = DetailCategoryFragment()
            val mBundle = Bundle()

            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            mDetailCategoryFragment.arguments = mBundle //cara 1 menggunakan bundle
            mDetailCategoryFragment.description = description //cara 2 dengan getter set

            val mFragmentManager = parentFragmentManager
//            mFragmentManager.beginTransaction().apply {
//                replace(
//                    R.id.frame_container,
//                    mDetailCategoryFragment,
//                    DetailCategoryFragment::class.java.simpleName
//                )
//                addToBackStack(null)
//                commit()
//
//            }
            mFragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.frame_container,
                    mDetailCategoryFragment,
                    DetailCategoryFragment::class.java.simpleName
                )
            }
        }
    }
}