package com.kaanyagan.vkihesapla

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaanyagan.vkihesapla.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heightPicker.minValue=100
        binding.heightPicker.maxValue=250
        binding.heightPicker.value=160

        binding.weightPicker.minValue=30
        binding.weightPicker.maxValue=150
        binding.weightPicker.value=50

        calculateVKI()

        binding.heightPicker.setOnValueChangedListener{_,_,_ ->
            calculateVKI()
        }

        binding.weightPicker.setOnValueChangedListener{_,_,_ ->
            calculateVKI()
        }

        binding.infoButton.setOnClickListener {
            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(this@MainActivity)

            builder.setMessage(resources.getString(R.string.info_content))
            builder.setTitle(resources.getString(R.string.info_title))
            builder.setCancelable(true)
            builder.create()?.show()
        }
    }

    private fun calculateVKI()
    {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.weightPicker.value

        val vki = weight.toDouble() / (doubleHeight * doubleHeight)
        changeBackgroundColor(vki)
        binding.scoreTV.text = String.format("%.2f", vki)
    }

    private fun changeBackgroundColor(vki: Double){
        if (vki < 18.5)
        {
            binding.mainLL.setBackgroundResource(R.drawable.bg_underweight)
            binding.resultTV.text = resources.getString(R.string.underweight)
            binding.scoreTV.setBackgroundResource(R.drawable.bg_score_underweight)
            binding.resultIcon.setBackgroundResource(R.drawable.icon_result_underweight)
            binding.resultContent.text = resources.getString(R.string.content_underweight)
        }
        else if (vki < 24.99){
            binding.mainLL.setBackgroundResource(R.drawable.bg_normal)
            binding.resultTV.text = resources.getString(R.string.normal)
            binding.scoreTV.setBackgroundResource(R.drawable.bg_score_normal)
            binding.resultIcon.setBackgroundResource(R.drawable.icon_result_normal)
            binding.resultContent.text = resources.getString(R.string.content_normal)
        }
        else if (vki < 29.99){
            binding.mainLL.setBackgroundResource(R.drawable.bg_overweight)
            binding.resultTV.text = resources.getString(R.string.overweight)
            binding.scoreTV.setBackgroundResource(R.drawable.bg_score_overweight)
            binding.resultIcon.setBackgroundResource(R.drawable.icon_result_overweight)
            binding.resultContent.text = resources.getString(R.string.content_overweight)
        }
        else{
            binding.mainLL.setBackgroundResource(R.drawable.bg_obesity)
            binding.resultTV.text = resources.getString(R.string.obesity)
            binding.scoreTV.setBackgroundResource(R.drawable.bg_score_obesity)
            binding.resultIcon.setBackgroundResource(R.drawable.icon_result_obesity)
            binding.resultContent.text = resources.getString(R.string.content_obesity)
        }
    }

}