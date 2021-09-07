package com.testproject.feature.tododetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testproject.R
import com.testproject.base.BaseFragment
import com.testproject.databinding.FrTodoDetailsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TodoDetailsFragment : BaseFragment(R.layout.fr_todo_details) {

    private val args: TodoDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<TodoDetailsViewModel> {
        parametersOf(args.todoItemId)
    }
    private val binding: FrTodoDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.viewStateFlow.collect(::setViewState)
        }
    }

    private fun setViewState(state: TodoDetailsViewState) {
        state.todoItem?.let { todoItem ->
            with(binding) {
                tvTitle.text = todoItem.title
                tvStatus.text = getString(R.string.status_label, todoItem.status)
                tvDueOn.text = getString(R.string.due_on_label, todoItem.dueOn)
            }
        }
    }
}
