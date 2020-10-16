package ma.sample.usertasks.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ma.sample.usertasks.data.repository.UserTasksRepository

class UsersViewModel @ViewModelInject constructor(private val repository: UserTasksRepository)
    : ViewModel() {

    val users = repository.getUsers()

}