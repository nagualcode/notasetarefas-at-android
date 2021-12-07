package com.infnet.app.cadastroestudantesAT.di

import androidx.room.Room
import android.content.Context
import com.infnet.app.cadastroestudantesAT.db.StudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable


@InstallIn(ApplicationComponent::class)
@Module
class AppModule{


    @Provides
    @DbName
    fun provideDbName() = "studentdata.db"


    @Provides fun provideStudentDatabase(@ApplicationContext context: Context, @DbName dbName: String): StudentDatabase{
        return Room.databaseBuilder(context,
                StudentDatabase::class.java, dbName)
                .build()
    }

    @Provides fun provideStudentDao(studentDatabase: StudentDatabase) = studentDatabase.studentDao()

    @Provides fun provideCompositeDisposable() = CompositeDisposable()
}