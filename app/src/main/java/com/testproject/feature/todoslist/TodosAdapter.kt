package com.testproject.feature.todoslist

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.testproject.R
import com.testproject.databinding.ItemTodoBinding
import com.testproject.domain.entities.TodoItem
import com.testproject.utils.itemCallback

fun todoItemAdapterDelegate(
    onItemClicked: (id: Int) -> Unit
) =
    adapterDelegateViewBinding<TodoItem, TodoItem, ItemTodoBinding>(
        { layoutInflater, parent -> ItemTodoBinding.inflate(layoutInflater, parent, false) }
    ) {
        binding.root.setOnClickListener {
            onItemClicked(item.id)
        }

        bind {
            with(binding) {
                tvTitle.text = item.title
                tvStatus.text = getString(R.string.status_label, item.status)
            }
        }
    }

internal val todoItemDiffCallback = itemCallback<TodoItem>(
    areItemsTheSame = { oldItem, newItem ->
        oldItem.id == newItem.id
    },
    areContentsTheSame = { oldItem, newItem ->
        oldItem == newItem
    },
    getChangePayload = { _, newItem ->
        newItem
    }
)

class TodosAdapter(
    onItemClicked: (id: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<TodoItem>(
    todoItemDiffCallback
) {
    init {
        delegatesManager.addDelegate(todoItemAdapterDelegate(onItemClicked))
    }
}
