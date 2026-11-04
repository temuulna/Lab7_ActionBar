package com.example.lab7_actionbar


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // 액션바의 찬성/반대 숫자 텍스트
    lateinit var m_OkCount: TextView
    lateinit var m_NoCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()  ← 액션바와 겹치지 않게 사용 안 함
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_ok: Button = findViewById(R.id.button_ok)
        val button_no: Button = findViewById(R.id.button_no)

        // 찬성 버튼 → 액션바 👍 숫자 +1
        button_ok.setOnClickListener {
            val num = Integer.parseInt(m_OkCount.text.toString()) + 1
            m_OkCount.text = num.toString()
        }
        // 반대 버튼 → 액션바 👎 숫자 +1
        button_no.setOnClickListener {
            val num = Integer.parseInt(m_NoCount.text.toString()) + 1
            m_NoCount.text = num.toString()
        }
    }

    // 액션바 메뉴 만들기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        // 찬성 메뉴에 ok_layout 적용 후, 그 안의 숫자 TextView 찾기
        val item_ok = menu?.findItem(R.id.menu_up)
        item_ok?.setActionView(R.layout.ok_layout)
        val oklayout = item_ok?.actionView
        if (oklayout != null) m_OkCount = oklayout.findViewById(R.id.thumbs_up_num)

        // 반대 메뉴에 no_layout 적용
        val item_no = menu?.findItem(R.id.menu_down)
        item_no?.setActionView(R.layout.no_layout)
        val nolayout = item_no?.actionView
        if (nolayout != null) m_NoCount = nolayout.findViewById(R.id.thumbs_up_num)

        return super.onCreateOptionsMenu(menu)
    }

    // ⋮ 메뉴 선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_init -> {          // 초기화
                m_OkCount.text = "0"
                m_NoCount.text = "0"
                return true
            }
            R.id.menu_close -> {         // 종료
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}