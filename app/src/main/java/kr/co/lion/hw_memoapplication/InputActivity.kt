package kr.co.lion.hw_memoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kr.co.lion.hw_memoapplication.databinding.ActivityInputBinding
import java.time.LocalDate

class InputActivity : AppCompatActivity() {
    lateinit var binding : ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
    }

    // 툴바 설정
    fun setToolbar() {
        binding.apply {
            inputToolbar.apply {
                inflateMenu(R.menu.input_menu)

                setNavigationIcon(R.drawable.ic_back)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_item_done -> {
                            checkInput()
                        }
                    }
                    true
                }
            }
        }
    }

    // 유효성 검사
    fun checkInput() {
        binding.apply {
            val title = inputTitleEdit.text.toString()
            if (title.trim().isEmpty()) {
                Toast.makeText(this@InputActivity,"제목을 입력해주세요", Toast.LENGTH_LONG).show()
            }

            val content = inputContentEdit.text.toString()
            if (content.trim().isEmpty()) {
                Toast.makeText(this@InputActivity, "내용을 입력해주세요", Toast.LENGTH_LONG).show()
            }

            if(title.isNotEmpty() && content.isNotEmpty()) {
                addMemoData()
                val resultIntent = Intent()
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    // 메모 저장
    fun addMemoData() {
        binding.apply {
            val memo = Memo()
            memo.title = inputTitleEdit.text.toString()
            memo.content = inputContentEdit.text.toString()
            memo.date = LocalDate.now()

            Util.memoList.add(memo)

            Log.e("memoData", "메모 입력 저장 : ${memo.title}, ${memo.content}, ${memo.date}")
            // 로그로 MemoList 내용 출력
            for (memoItem in Util.memoList) {
                Log.d("memoData", "Title: ${memoItem.title}, Content: ${memoItem.content}, Date: ${memoItem.date}")
            }
        }
    }
}