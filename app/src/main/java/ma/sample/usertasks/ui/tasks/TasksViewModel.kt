package ma.sample.usertasks.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import ma.sample.usertasks.data.entity.Task
import ma.sample.usertasks.data.repository.UserTasksRepository
import ma.sample.usertasks.utils.Progress

class TasksViewModel @ViewModelInject constructor(
    private val repository: UserTasksRepository) : ViewModel() {

    private val idUser = MutableLiveData<String>()

    private val _tasks = idUser.switchMap { id ->
        repository.getUserTasks(id)
    }

    val tasks: LiveData<Progress<List<Task>>> = _tasks


    fun start(id: String) {
        idUser.value = id
    }
}