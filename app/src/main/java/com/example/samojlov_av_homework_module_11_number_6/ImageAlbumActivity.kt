package com.example.samojlov_av_homework_module_11_number_6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.samojlov_av_homework_module_11_number_6.databinding.ActivityImageAlbumBinding

class ImageAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageAlbumBinding
    private lateinit var toolbarImageAlbum: androidx.appcompat.widget.Toolbar
    private lateinit var imageViewIV: ImageView
    private lateinit var nextFotoBT: Button
    private val imageList: MutableList<Int> = mutableListOf()
    lateinit var currentViewModel: CurrentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImageAlbumBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_image_album)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

    }

    private fun init() {
        toolbarImageAlbum = binding.toolbarMain
        setSupportActionBar(toolbarImageAlbum)
        title = getString(R.string.toolbar_title)
        toolbarImageAlbum.subtitle = getString(R.string.toolbar_subtitle)
        toolbarImageAlbum.setLogo(R.drawable.image_icon)

        currentViewModel = ViewModelProvider(this)[CurrentViewModel::class.java]

        imageViewIV = binding.imageViewIV
        nextFotoBT = binding.nextFotoBT

        fillingImageList()

        imageViewIV.setImageResource(imageList[0])



        currentViewModel.currentCounter.observe(this) {
            if (currentViewModel.counter == imageList.size) startLastActivity()
            else imageViewIV.setImageResource(imageList[it])
        }

        nextFotoBT.setOnClickListener {
            currentViewModel.currentCounter.value = ++currentViewModel.counter
        }
    }

    private fun startLastActivity() {
        val intent = Intent(this, LastActivity::class.java)
        startActivity(intent)
    }

    private fun fillingImageList() {
        imageList.add(R.drawable.image_one)
        imageList.add(R.drawable.image_two)
        imageList.add(R.drawable.image_three)
        imageList.add(R.drawable.image_four)
        imageList.add(R.drawable.image_five)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit),
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}