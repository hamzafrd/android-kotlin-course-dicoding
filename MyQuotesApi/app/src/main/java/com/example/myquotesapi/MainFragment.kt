package com.example.myquotesapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myquotesapi.databinding.FragmentMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://quote-api.dicoding.dev/random"
        getRandomQuotes(url)
        val btnAllList = view.findViewById<Button>(R.id.btnAllQuotes)
        btnAllList.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAllQuotes -> {
                val mCategoryFragment = ListQuoteFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.frame_container,
                        mCategoryFragment,
                        ListQuoteFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    private fun getRandomQuotes(url: String) {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                binding.progressBar.visibility = View.INVISIBLE

                val result = String(responseBody!!)
                Log.d(MainActivity.TAG, statusCode.toString())
                Log.d(MainActivity.TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val quote = responseObject.getString("en")
                    val author = responseObject.getString("author")
                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                // Jika koneksi gagal
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}