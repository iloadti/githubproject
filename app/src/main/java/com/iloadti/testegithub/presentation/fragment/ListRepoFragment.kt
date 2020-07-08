package com.iloadti.testegithub.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iloadti.testegithub.di.loadModuleListRepo
import com.iloadti.testegithub.extension.addEndScrollListener
import com.iloadti.testegithub.commons.AbstractFragment
import com.iloadti.testegithub.domain.entities.RepoGitHubEntity
import com.iloadti.testegithub.R
import com.iloadti.testegithub.presentation.MainActivity
import com.iloadti.testegithub.presentation.adapter.ListRepoAdapter
import com.iloadti.testegithub.presentation.state.ErrorState
import com.iloadti.testegithub.presentation.state.ListRepoState
import com.iloadti.testegithub.presentation.viewmodel.ListRepoViewModel
import kotlinx.android.synthetic.main.fragment_list_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ListRepoFragment : AbstractFragment() {

    private val viewModel: ListRepoViewModel by viewModel()

    init {
        loadModuleListRepo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_repo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(R.string.title_toolbar_list_repo)

        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            addEndScrollListener {
                viewModel.getListRepo()
            }
        }

        initObservers()
        viewModel.getListRepo()
    }

    private fun showList(items: List<RepoGitHubEntity>, lastPage: Boolean) {
        if (recyclerView?.adapter is ListRepoAdapter) {
            (recyclerView?.adapter as? ListRepoAdapter)?.items = items
            (recyclerView?.adapter as? ListRepoAdapter)?.lastPage = lastPage
            recyclerView?.adapter?.notifyDataSetChanged()
        } else {
            recyclerView?.adapter = ListRepoAdapter(context, items) {
                (activity as? MainActivity)?.showPullRequests(it)
            }
            recyclerView?.announceForAccessibility(
                getString(
                    R.string.accessibility_list_count,
                    recyclerView?.adapter?.itemCount
                )
            )
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ListRepoState.SuccessRepoList -> showList(state.items, state.lastPage)
            }
        })

        viewModel.stateError.observe(viewLifecycleOwner, Observer { state ->
            if (state is ErrorState.ShowError)
                Toast.makeText(context, getString(state.idRes), Toast.LENGTH_LONG).show()
        })
    }
}