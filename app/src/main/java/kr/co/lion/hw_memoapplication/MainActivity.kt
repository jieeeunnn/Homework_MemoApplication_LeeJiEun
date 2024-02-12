package kr.co.lion.hw_memoapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.hw_memoapplication.databinding.ActivityMainBinding
import kr.co.lion.hw_memoapplication.databinding.MemoListBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var showActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLauncher()
        setToolbar()
        setView()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.mainRecyclerView.adapter?.notifyDataSetChanged()
    }

    // 툴바 설정
    fun setToolbar() {
        binding.apply {
            mainToolbar.apply {
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when (it.itemId) {
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
    @SuppressLint("NotifyDataSetChanged")
    fun setLauncher() {
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // MemoList에 변화가 있을 때 어댑터 갱신
                binding.mainRecyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val contract2 = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(contract2) {

        }
    }

    fun setView() {
        binding.apply {
            mainRecyclerView.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)

                // 아이템 사이 간격 조정
                val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)

                mainRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        outRect.bottom = spacingInPixels
                    }
                })

            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MemoViewHolder>() {
        inner class MemoViewHolder(memoBinding: MemoListBinding) :
            RecyclerView.ViewHolder(memoBinding.root) {
            val memoBinding: MemoListBinding

            init {
                this.memoBinding = memoBinding

                this.memoBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
            val memoBinding = MemoListBinding.inflate(layoutInflater)
            val viewHolderMemo = MemoViewHolder(memoBinding)

            return viewHolderMemo
        }

        override fun getItemCount(): Int {
            return Util.memoList.size
        }

        override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
            val memoList = Util.memoList
            val memo = memoList[position]

            holder.memoBinding.memoListTitle.text = memo.title
            holder.memoBinding.memoListDate.text = memo.date.toString()

            holder.memoBinding.root.setOnClickListener {
                val intent = Intent(this@MainActivity, ShowActivity::class.java)
                intent.putExtra("position", position)
                showActivityLauncher.launch(intent)
            }
        }
    }
}