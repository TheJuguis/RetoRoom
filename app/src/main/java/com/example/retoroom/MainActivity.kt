package com.example.retoroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.retoroom.database.User
import com.example.retoroom.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel.getUsers()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener{


            mainViewModel.saveUser(
                User(
                    0,
                    user_name = binding.etNombresDB.text.toString()
                )
            )
            mainViewModel.getUsers()

        }
        mainViewModel.getUsers()
        mainViewModel.savedUsers.observe(this) {usersList ->
            if(!usersList.isNullOrEmpty()){
                for(user in usersList){
                    Log.d("thesearetheusers", "user: ${user.user_name} id ${user.user_id}")

                    binding.rvNombresEntries.adapter = MainAdapter(usersList)
                }
            }else{
                Log.d("thesearetheusers", "null or empty")
            }
        }

        binding.btnEliminar.setOnClickListener {
            val ides = binding.tvDelId.text.toString()
            Integer.parseInt(ides)
            val ides2 = ides.toInt()
            mainViewModel.deleteUser(

                User(
                user_id = ides2,
                user_name = binding.tvDelUser.text.toString()
            ))
            Log.d("thesearetheusers", "ides2 : ${ides2}")


            mainViewModel.getUsers()

        }





    }
}