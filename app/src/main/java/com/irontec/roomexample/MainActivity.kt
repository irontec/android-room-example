package com.irontec.roomexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import com.irontec.roomexample.database.AppDatabase
import com.irontec.roomexample.database.entities.Bill
import com.irontec.roomexample.database.entities.Customer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customers.setOnClickListener({ _ ->
            val intent = Intent(this@MainActivity, CustomersActivity::class.java)
            startActivity(intent)
        })

        bills.setOnClickListener({ _ ->
            val intent = Intent(this@MainActivity, BillsActivity::class.java)
            startActivity(intent)
        })

        new_bill.setOnClickListener({ _ ->
            doAsync {

                val customers = AppDatabase.getInstance(this@MainActivity).customerDao().all
                val names: MutableList<CharSequence> = arrayListOf()
                customers.asSequence()
                        .mapTo(names) {
                            String.format("%s %s", it.firstName, it.lastName)
                        }

                uiThread {

                    selector(getString(R.string.dialog_title), names, { _, i ->

                        alert {
                            customView {
                                verticalLayout {
                                    val amount = editText {
                                        inputType = InputType.TYPE_CLASS_NUMBER
                                        padding = dip(16)
                                        hint = getString(R.string.dialog_hint)
                                    }
                                    positiveButton("Register") {
                                        saveAmount(amount.text.toString(), customers.get(i))
                                    }
                                }
                            }
                        }.show()
                        toast("So you're living in ${names[i]}, right?")
                    })

                }
            }

        })

        providers.setOnClickListener({ _ ->
            val intent = Intent(this@MainActivity, ProvidersActivity::class.java)
            startActivity(intent)
        })

    }

    private fun saveAmount(amount: String, customer: Customer) {
        doAsync {
            val bill = Bill(amount = Integer.valueOf(amount), customerId = customer.uid)
            AppDatabase.getInstance(this@MainActivity).billDao().insert(bill)
        }
    }
}
