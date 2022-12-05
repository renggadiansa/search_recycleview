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
        mList.add(LanguageData("JAVA" , R.drawable.java,
            "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible."))
        mList.add(LanguageData("KOTLIN" , R.drawable.kotlin,
            "Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise."))
        mList.add(LanguageData("C++" , R.drawable.cplusplus,
            "C++ is a general-purpose programming language created by Bjarne Stroustrup as an extension of the C programming language, or \"C with Classes\". The language has expanded significantly over time, and modern C++ now has object-oriented, generic, and functional features in addition to facilities for low-level memory manipulation."))
        mList.add(LanguageData("HTML" , R.drawable.html,
            "The HyperText Markup Language or HTML is the standard markup language for documents designed to be displayed in a web browser."))
        mList.add(LanguageData("PYTHON" , R.drawable.python,
            "Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation."))
        mList.add(LanguageData("SWIFT" , R.drawable.swift,

            "Swift is a general-purpose, multi-paradigm, compiled programming language developed by Apple Inc. and the open-source community."))
        mList.add(LanguageData("C#" , R.drawable.csharp,
            "C# is a general-purpose, multi-paradigm programming language encompassing strong typing, lexically scoped, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines."))
        mList.add(LanguageData("JAVASCRIPT" , R.drawable.javascript,
            "JavaScript, often abbreviated as JS, is a programming language that conforms to the ECMAScript specification. JavaScript is high-level, often just-in-time compiled, and multi-paradigm."))
    }
}