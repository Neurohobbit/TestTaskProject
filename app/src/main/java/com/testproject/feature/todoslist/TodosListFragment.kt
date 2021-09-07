package com.testproject.feature.todoslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testproject.R
import com.testproject.base.BaseFragment
import com.testproject.databinding.FrTodosListBinding
import com.testproject.utils.LineItemDecorator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class TodosListFragment : BaseFragment(R.layout.fr_todos_list) {

    private val viewModel by viewModel<TodosListViewModel>()
    private val binding: FrTodosListBinding by viewBinding()

    private val todosAdapter by lazy { TodosAdapter(viewModel::onItemClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecycler()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.viewStateFlow.collect(::setViewState)
        }
        lifecycleScope.launch {
            viewModel.actionFlow.collect { action ->
                when (action) {
                    is TodoListAction.ShowTodoDetailsScreen -> {
                        navigate(TodosListFragmentDirections.actionTodosListFragmentToTodoDetailsFragment(action.id))
                    }
                }
            }
        }
    }

    private fun setViewState(state: TodosListViewState) {
        todosAdapter.items = state.todosList
    }

    private fun initRecycler() {
        binding.rvTodos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todosAdapter
            addItemDecoration(
                LineItemDecorator(
                    context = requireContext(),
                    lineColor = R.color.tint,
                    lineWidthId = R.dimen.divider_width
                )
            )
        }
    }
}
