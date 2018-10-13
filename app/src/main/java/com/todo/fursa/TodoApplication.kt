package com.todo.fursa

import android.app.Application
import com.todo.fursa.dagger.component.AppComponent
import com.todo.fursa.dagger.component.DaggerAppComponent
import com.todo.fursa.dagger.module.AppModule
import com.todo.fursa.dagger.module.ManageModule
import com.todo.fursa.dagger.module.RoomModule


class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule(this))
                .manageModule(ManageModule(this))
                .build()

        component.inject(this)

    }


    companion object {
        lateinit var component: AppComponent
    }

}