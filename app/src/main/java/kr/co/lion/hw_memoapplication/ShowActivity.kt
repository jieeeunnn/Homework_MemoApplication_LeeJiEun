package kr.co.lion.hw_memoapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.hw_memoapplication.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowBinding
    lateinit var modifyActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setView()
        setLauncher()

    }

    fun setToolbar() {
        binding.apply {
            showToolbar.apply {
                inflateMenu(R.menu.show_menu)

                setNavigationIcon(R.drawable.ic_back)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 수정
                        R.id.menu_item_modify -> {
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)
                            val position = intent.getIntExtra("position", 0)
                            modifyIntent.putExtra("position", position)

                            modifyActivityLauncher.launch(modifyIntent)
                        }
                        // 삭제
                        R.id.menu_item_delete -> {
                            val position = intent.getIntExtra("position", 0)
                            Util.memoList.removeAt(position)
                            finish()
                        }
                    }
                    true
                }
            }
        }
    }

    fun setLauncher() {
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val position = intent.getIntExtra("position", 0)
                // 수정된 메모의 위치를 받아와서 해당 위치의 메모를 가져와서 화면에 반영
                val updatedMemo = Util.memoList[position]
                binding.showTitle.setText(updatedMemo.title)
                binding.showContent.setText(updatedMemo.content)
            }
        }
    }

    fun setView() {
        binding.apply {
            val position = intent.getIntExtra("position", 0)
            val memo = Util.memoList[position]

            showTitle.setText(memo.title)
            showDate.setText(memo.date.toString())
            showContent.setText(memo.content)
        }
    }
}