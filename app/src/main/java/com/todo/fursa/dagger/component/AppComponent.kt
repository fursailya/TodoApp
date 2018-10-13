package com.todo.fursa.dagger.component

import com.todo.fursa.TodoApplication
import com.todo.fursa.alarm.AlarmReceiver
import com.todo.fursa.alarm.NotificationHelper
import com.todo.fursa.dagger.module.AppModule
import com.todo.fursa.dagger.module.ManageModule
import com.todo.fursa.dagger.module.RoomModule
import com.todo.fursa.room.repository.MainRepository
import com.todo.fursa.ui.base.BaseActivity
import com.todo.fursa.ui.fragment.AddTodoBottomSheet
import com.todo.fursa.ui.viewmodel.InsertAsyncTask
import com.todo.fursa.ui.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, ManageModule::class])
interface AppComponent {
    fun inject(viewModel: MainViewModel)
    fun inject(todoApplication: TodoApplication)
    fun inject(addTodoBottomSheet: AddTodoBottomSheet)
    fun inject(insertAsyncTask: InsertAsyncTask)
    fun inject(alarmReceiver: AlarmReceiver)
    fun inject(notificationHelper: NotificationHelper)
    fun inject(mainRepository: MainRepository)
    fun inject(baseActivity: BaseActivity)
}