package com.yousef.mvvmflightinfo.ui.schedules

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class SchedulesModule {
@Provides
fun provideStudentSessionAdapter(): SchedulesAdapter {
    return SchedulesAdapter(ArrayList())
}

@Provides
fun provideLinearLayoutManager(activity: SchedulesActivity?): LinearLayoutManager {
    return LinearLayoutManager(activity)
}
}