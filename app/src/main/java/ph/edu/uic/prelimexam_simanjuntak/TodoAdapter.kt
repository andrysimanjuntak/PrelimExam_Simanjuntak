package ph.edu.uic.prelimexam_simanjuntak

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.edu.uic.prelimexam_simanjuntak.databinding.ItemTodoBinding


private lateinit var binding: ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    //class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        /*return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )

        */
        return TodoViewHolder(binding)
    }


    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            binding.tvTodoTitle.text = curTodo.title
            binding.cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(binding.tvTodoTitle, curTodo.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(binding.tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}