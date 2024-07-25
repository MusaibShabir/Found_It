import com.example.foundit.presentation.data.local.repo.ProfileDataRepository
import com.example.foundit.presentation.screens.profile.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideViewModel(repository: ProfileDataRepository): ProfileViewModel {
        return ProfileViewModel(repository)
    }

}