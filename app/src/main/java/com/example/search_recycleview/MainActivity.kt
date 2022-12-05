package com.example.search_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchView: SearchView
    private var mList= ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleview)
        searchView = findViewById(R.id.search)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()

        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query : String?){
        if(query != null){
            val filteredList = ArrayList<LanguageData>()
            for(item in mList){
                if (item.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(item)
                }
            }
           if (filteredList.isEmpty()){
               Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
           }else{
                adapter.setFilteredList(filteredList)
           }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("JAVA" , R.drawable.java))
        mList.add(LanguageData("KOTLIN" , R.drawable.kotlin))
        mList.add(LanguageData("C++" , R.drawable.cplusplus))
        mList.add(LanguageData("HTML" , R.drawable.html))
        mList.add(LanguageData("PYTHON" , R.drawable.python))
        mList.add(LanguageData("SWIFT" , R.drawable.swift))
        mList.add(LanguageData("C#" , R.drawable.csharp))
        mList.add(LanguageData("JAVASCRIPT" , R.drawable.javascript))
    }
}