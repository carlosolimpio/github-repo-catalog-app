package com.demo.kointest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.kointest.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FetchDataFragment : Fragment() {
    private val viewModel: FetchDataViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_fetch_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData()

        viewModel.repoList.observe(viewLifecycleOwner) {
            it.forEach { repo ->
                Log.d("KOINTEST", "REPO: $repo")
            }
        }
    }
}
