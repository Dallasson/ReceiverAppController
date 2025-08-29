package com.app.receiverappcontroller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.ipc_library.ApiController
import com.app.ipc_library.IpcApiController
import com.app.receiverappcontroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var apiController: ApiController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // Setup ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        apiController = IpcApiController(
            context = this,
            targetPackage = "com.app.ipcreceiver",
            serviceClassName = "com.app.ipcreceiver.IControlService"
        )

        binding.receiverEditTextBtn.setOnClickListener {
            apiController.enableEditText(
                shouldSendEditText = false
            )
        }

        binding.receiverJsonFileBtn.setOnClickListener {
            apiController.enableJsonFile(
                shouldSendJsonFile = false
            )
        }

        binding.receiverJsonApiBtn.setOnClickListener {
            apiController.enableJsonApi(
                shouldSendJsonApi = false
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
       // apiController.unbindService() // Add this method inside your IpcApiController
    }
}