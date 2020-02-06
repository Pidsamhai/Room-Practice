package com.mengxyz.room.room.practice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mengxyz.room.room.practice.db.adapter.WordAdapter
import com.mengxyz.room.room.practice.db.entity.Word
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter =  WordAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.allWorls.observe(this, Observer {
            it?.let {
                Log.e("ViewModel", "Added ")
                adapter.setWorlds(it)
            }
        })

        btnAdd.setOnClickListener {
            val i = Intent(this,NewWorld::class.java)
            startActivityForResult(i,newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(NewWorld.EXTRA_REPLY)?.let{
                val w = Word(it)
                viewModel.insert(w)
            }
        }
        else{
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }
    }
}