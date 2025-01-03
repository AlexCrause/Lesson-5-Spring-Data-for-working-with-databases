package com.example.task_list_management;

import com.example.task_list_management.models.Task;
import com.example.task_list_management.models.TaskStatus;
import com.example.task_list_management.repositories.TaskRepository;
import com.example.task_list_management.servicies.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class TaskListManagementApplicationTests {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    /**
     * Тест для успешного обновления статуса задачи.
     *
     * Шаги:
     * 1. Arrange: Мокаем репозиторий задач, чтобы он возвращал задачу при поиске по ID
     *    и сохранял обновленную задачу в репозиторий.
     * 2. Act: Вызываем метод updateTaskStatus с действительным ID задачи и новым статусом.
     * 3. Assert:
     *    - Результат должен быть Optional, содержащий обновленную задачу.
     *    - Статус задачи должен быть обновлен на новый.
     *    - Методы findById и save репозитория должны быть вызваны ровно один раз.
     */
    @Test
    public void updateTaskStatusTest_Success() {
        // Arrange
        Long taskId = 1L; // ID задачи для обновления
        TaskStatus newStatus = TaskStatus.COMPLETED; // Новый статус для задачи
        Task task = new Task();
        task.setId(taskId);
        task.setStatus(TaskStatus.IN_PROGRESS); // Устанавливаем начальный статус

        // Мокаем поведение репозитория: findById возвращает задачу, save возвращает обновленную задачу
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        // Act
        Optional<Task> updatedTask = taskService.updateTaskStatus(taskId, newStatus);

        // Assert
        Assertions.assertTrue(updatedTask.isPresent()); // Проверяем, что результат не пустой
        Assertions.assertEquals(newStatus, updatedTask.get().getStatus()); // Проверяем, что статус обновлен
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId); // Проверяем, что findById был вызван один раз
        Mockito.verify(taskRepository, Mockito.times(1)).save(task); // Проверяем, что save был вызван один раз
    }

    /**
     * Тест для обработки ситуации, когда задача не найдена в репозитории.
     *
     * Шаги:
     * 1. Arrange: Мокаем репозиторий задач, чтобы он возвращал пустой Optional при поиске по ID.
     * 2. Act: Вызываем метод updateTaskStatus с недействительным ID задачи.
     * 3. Assert:
     *    - Результат должен быть пустым Optional, так как задача не найдена.
     *    - Метод findById репозитория должен быть вызван ровно один раз.
     *    - Метод save репозитория не должен быть вызван.
     */
    @Test
    public void updateTaskStatusTest_TaskNotFound() {
        // Arrange
        Long taskId = 1L; // ID несуществующей задачи
        TaskStatus newStatus = TaskStatus.COMPLETED; // Новый статус для задачи

        // Мокаем поведение репозитория: findById возвращает пустой Optional
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act
        Optional<Task> updatedTask = taskService.updateTaskStatus(taskId, newStatus);

        // Assert
        Assertions.assertTrue(updatedTask.isEmpty()); // Проверяем, что результат пустой
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId); // Проверяем, что findById был вызван один раз
        Mockito.verify(taskRepository, Mockito.never()).save(Mockito.any()); // Проверяем, что save не был вызван
    }
}
