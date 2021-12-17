package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.model.FoodItem
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.android.synthetic.main.fragment_registration.thumbnail

class AddProductFragment : Fragment(), View.OnClickListener {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var uri: Uri
    private lateinit var downloadUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.setOnClickListener {
            com.github.dhaval2404.imagepicker.ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        add_food.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            animationView.visibility = View.VISIBLE
            uri = data?.data!!
            thumbnail.setImageURI(uri)
            uploadImage(uri)
        }
    }

    private fun uploadImage(uri: Uri) {
        val storageReference = FirebaseStorage.getInstance()
            .getReference("Food Images")

        storageReference.putFile(uri)
            .addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful) {
                    animationView.visibility = View.GONE
                    downloadUrl = it.result.toString()
                } else {
                    Toast.makeText(requireContext(), "Upload unsuccessful", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .addOnFailureListener(OnFailureListener {
                animationView.visibility = View.GONE
                Toast.makeText(requireContext(), "Upload unsuccessful", Toast.LENGTH_SHORT).show()
            })

    }

    override fun onClick(v: View?) {
        if (food_name.text.toString().isEmpty() || food_desc.text.toString()
                .isEmpty() || food_price.text.toString().isEmpty()
        ) {
            Toast.makeText(requireContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show()
        } else {

            val foodItem = FoodItem(
                food_name.text.toString(),
                downloadUrl,
                food_desc.text.toString(),
                Integer.parseInt(food_price.text.toString()),
                0,
                ""
            )

            animationView.visibility = View.VISIBLE

            uploadFoodToDb(foodItem)

            Handler().postDelayed(Runnable {
                animationView.visibility = View.GONE
            }, 1200)
        }
    }

    private fun uploadFoodToDb(foodItem: FoodItem) {
        FirebaseFirestore.getInstance()
            .collection("food")
            .document()
            .set(foodItem)
            .addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Food item is added successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}