package com.muratcakin.poco.feature.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.muratcakin.poco.feature.main.MainActivity
import com.muratcakin.poco.R
import com.muratcakin.poco.databinding.ActivityOnboardingBinding
import com.muratcakin.poco.feature.onboarding.adapter.OnBoardingAdapter
import com.muratcakin.poco.utils.extensions.gone
import com.muratcakin.poco.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {
    private val viewModel by viewModels<OnBoardingViewModel>()
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = OnBoardingAdapter(this, prepareOnBoardingItems())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

        initViews()
    }

    private fun initViews() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.isLastPage = position == 2
                if (position != 0) {
                    binding.btnPrev.visible()
                } else {
                    binding.btnPrev.gone()
                }
            }
        })

        binding.btnSkip.setOnClickListener {
            skipOnBoarding()
        }

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem == 2) {
                skipOnBoarding()
            } else {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem.plus(1), true)
            }
        }

        binding.btnPrev.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem.minus(1), true)
        }
    }

    private fun skipOnBoarding() {
        viewModel.setOnBoardingStatus()
        navigateToMain()
    }

    private fun prepareOnBoardingItems(): List<Int> {
        return listOf(
            R.layout.item_onboarding,
            R.layout.item_onboarding_two,
            R.layout.item_onboarding_three
        )
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.KEY_NAVIGATE_PRODUCTS, false)
        startActivity(intent)
        finish()
    }
}