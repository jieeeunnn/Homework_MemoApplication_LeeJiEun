package kr.co.lion.hw_memoapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.hw_memoapplication.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setView()
    }

    fun setToolbar() {
        binding.apply {
            modifyToolbar.apply {
                inflateMenu(R.menu.modify_menu)

                setNavigationIcon(R.drawable.ic_back)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_item_modify_done -> {
                            modifyMemo()

                            val position = intent.getIntExtra("position", 0)
                            val resultIntent = Intent().putExtra("position", position)
                            setResult(Activity.RESULT_OK, resultIntent)

                            finish()
                        }
                    }
                    true
                }
            }
        }
    }

    fun setView() {
        binding.apply {
            val position = intent.getIntExtra("position", 0)
            val memo = Util.memoList[position]

            modifyTitle.setText(memo.title)
            modifyContent.setText(memo.content)
        }
    }

    fun modifyMemo() {
        val position = intent.getIntExtra("position", 0)
        val memo = Util.memoList[position]

        binding.apply {
            memo.title = modifyTitle.text.toString()
            memo.content = modifyContent.text.toString()
        }
    }
}