package kr.co.lion.hw_memoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.hw_memoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var inputActivityLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLauncher()
        setToolbar()
    }

    // 툴바 설정
    fun setToolbar() {
        binding.apply {
            mainToolbar.apply {
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_item_edit_memo -> {
                            // 메모 작성 화면으로 이동
                            val intent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(intent)
                        }
                    }
                    true
                }
            }
        }
    }

    // 런처 설정
    fun setLauncher() {
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1) {

        }
    }
}