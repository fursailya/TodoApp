package com.todo.fursa.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.todo.fursa.R
import com.todo.fursa.databinding.ActivityDetailBinding
import com.todo.fursa.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.io.File
import java.io.IOException

const val LOG_TAG = "Todo/DetailActivity"

const val REQUEST_CODE_PICK_FROM_GALLERY = 1
const val REQUEST_CODE_TAKE_FROM_CAMERA = 2

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel
    private var id: Long = 0

    private lateinit var imageFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        id = intent.getLongExtra(MainActivity.EXTRA_NOTE_ID, 0)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.selectById(id).observe(this, Observer {
            detailBinding.todo = it
        })

        buttonDelete.setOnClickListener {
            viewModel.deleteById(id)
            startMainActivity()
        }

        imageButtonAttachFile.setOnClickListener { showAttachDialog() }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startMainActivity()
    }

    private fun startMainActivity() {
        val mainActivityIntent = Intent(this@DetailActivity, MainActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(mainActivityIntent)
        this.finish()
    }

    private fun showAttachDialog() {
        val dialog =  AlertDialog.Builder(this)
                .setTitle(R.string.attach_dialog_title)
                .setItems(R.array.attach_variants) { dialog, which ->  when(which) {
                    0 -> { addFromGallery() }
                    1 -> { takePhoto() }
                }}.create()

        dialog.show()
    }

    private fun addFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUEST_CODE_PICK_FROM_GALLERY)
    }

    private fun takePhoto() {
        val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val currentImageFile = createImageFile()


         if(currentImageFile != null) {
            val imageUri= FileProvider.getUriForFile(this, "com.todo.fursa.fileprovider", currentImageFile)
             photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
             startActivityForResult(photoIntent, REQUEST_CODE_TAKE_FROM_CAMERA)
        }


    }

    private fun createImageFile(): File? {
        val filename = "${System.currentTimeMillis()}.jpg"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        imageFile = File(storageDir, filename)

        try {

            if(imageFile.createNewFile()) {
                return imageFile
            }

        } catch (e: IOException) {
            e.stackTrace
        }

        return null
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            val imageUrl = data.data

            if(imageUrl != null) {
                val ins = contentResolver.openInputStream(imageUrl)
                val bitmap = BitmapFactory.decodeStream(ins)
                imageViewAttach.setImageBitmap(bitmap)
                imageViewAttach.visibility = View.VISIBLE
            }
        } else if(requestCode == REQUEST_CODE_TAKE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            imageViewAttach.setImageBitmap(bitmap)
            imageViewAttach.visibility = View.VISIBLE
        }
    }
}
