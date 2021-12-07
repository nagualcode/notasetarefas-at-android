package com.infnet.app.cadastroestudantesAT.di

import androidx.lifecycle.ViewModelProvider
import com.infnet.app.cadastroestudantesAT.vmfactory.StudentViewModelFactory


abstract class ViewModelFactoryModule {


    internal abstract fun bindViewModelFactory(vMFactory : StudentViewModelFactory) : ViewModelProvider.Factory
}