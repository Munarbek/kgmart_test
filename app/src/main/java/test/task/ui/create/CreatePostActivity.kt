package test.task.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import test.task.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreatePostBinding
    private  val  viewModel: CreateViewModel by viewModel<CreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Create post"
        setupListener()
        viewModel.create.observe(this) {
            if (it) {
                Toast.makeText(this, "Успешно создано", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Что-то пошло не так,попробуйте еще раз", Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
                binding.buttonCreate.isEnabled = true
            }
        }
    }

    private fun setupListener() {
        binding.buttonCreate.setOnClickListener {
            if (binding.textFieldTitle.editText?.text!!.isNotEmpty() && binding.textFieldTitle.editText?.text!!.isNotEmpty()) {
                val createPost = viewModel.createPost(
                    binding.textFieldTitle.editText?.text!!.toString(),
                    binding.textFieldDescription.editText?.text!!.toString()
                )
                binding.progressBar.visibility = View.VISIBLE
                binding.buttonCreate.isEnabled = false
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                binding.buttonCreate.isEnabled = true
            }
        }
    }
}