package psm.lab.w3_navigation_cyklzycia

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //viewModelOf(::ArtServiceVM) //to samo co pniżej tylko, że nowsze
    viewModel{ArtServiceVM(get())}
}