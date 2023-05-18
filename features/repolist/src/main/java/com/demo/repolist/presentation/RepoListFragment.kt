package com.demo.repolist.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.repolist.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListFragment : Fragment() {
    private val viewModel: GetRepoListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel.getRepoListForAndroidQuery()

        viewModel.repoList.observe(viewLifecycleOwner) {
            it.forEach { repo ->
                Log.d("GETREPO", "REPO: $repo")
            }
        }
    }
}
