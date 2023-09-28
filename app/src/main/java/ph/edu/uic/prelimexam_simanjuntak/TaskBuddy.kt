package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.uic.prelimexam_simanjuntak.databinding.ActivityTaskBuddyBinding


private lateinit var binding: ActivityTaskBuddyBinding
private lateinit var todoAdapter: TodoAdapter

class TaskBuddy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBuddyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())

        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

        binding.btnBackTaskBuddy.setOnClickListener {
            val email = intent.getStringExtra("email")
            val intent = Intent(this@TaskBuddy, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}