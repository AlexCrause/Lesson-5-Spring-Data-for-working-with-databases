package com.example.task_list_management.servicies;

import com.example.task_list_management.models.Task;
import com.example.task_list_management.models.TaskStatus;
import com.example.task_list_management.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Домашнее задание
 *
 * Условие:
 * Вам предстоит создать приложение для управления списком задач
 * с использованиемSpring Boot и Spring Data JPA.
 *
 * Требуется реализовать следующие функции:
 * 1. Добавление задачи.
 * 2. Просмотр всех задач.
 * 3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
 * 4. Изменение статуса задачи.
 * 5. Удаление задачи.
 *
 * Структура задачи:
 * - ID (автоинкрементное)
 * - Описание (не может быть пустым)
 * - Статус (одно из значений: "не начата", "в процессе", "завершена")
 * - Дата создания (автоматически устанавливается при создании задачи)
 */

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Добавление задачи
     * @param task
     * @return задача
     */
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Получение всех задач
     * @return список задач
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Получение задач по статусу
     * @param status
     * @return список задач по статусу
     */
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Изменение статуса задачи
     * @param id
     * @param status
     * @return  изменившаяся задача или пустой Optional
     */
    public Optional<Task> updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            return Optional.of(taskRepository.save(task));
        }
        return Optional.empty();
    }

    /**
     * Удаление задачи
     * @param id
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
