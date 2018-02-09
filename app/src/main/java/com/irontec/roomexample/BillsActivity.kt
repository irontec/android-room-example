package com.irontec.roomexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.irontec.roomexample.adapters.BillAdapter
import com.irontec.roomexample.database.AppDatabase
import kotlinx.android.synthetic.main.activity_bills.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BillsActivity : AppCompatActivity() {

    private var mAdapter: BillAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        mAdapter = BillAdapter(this@BillsActivity, mutableListOf())
        bills_list.adapter = mAdapter

        doAsync {

            val database = AppDatabase.getInstance(context = this@BillsActivity)
            val bills = database.billDao().all

            uiThread {
                mAdapter!!.addAll(bills)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.empty, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
