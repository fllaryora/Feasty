package com.darrenfinch.mymealplanner.common.dependencyinjection

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.darrenfinch.mymealplanner.model.FoodsRepository
import com.darrenfinch.mymealplanner.model.MealsRepository

//This composition root is scoped to a particular activity
class ActivityCompositionRoot(private val compositionRoot: CompositionRoot, private val activity: FragmentActivity) {
    fun getActivity(): FragmentActivity {
        return activity
    }
    fun getApplication(): Application {
        return compositionRoot.getApplication()
    }
    fun getFoodsRepository(): FoodsRepository {
        return compositionRoot.getFoodsRepository()
    }
    fun getMealsRepository(): MealsRepository {
        return compositionRoot.getMealsRepository()
    }
}