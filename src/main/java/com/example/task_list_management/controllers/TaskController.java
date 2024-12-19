package com.example.task_list_management.controllers;

import com.example.task_list_management.models.Task;
import com.example.task_list_management.models.TaskStatus;
import com.example.task_list_management.servicies.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Возвращает список всех задач
     * @return список задач
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Создает новую задачу
     * @param task
     * @return новая задача
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.addTask(task));
    }

    /**
     * Возвращает список задач по статусу
     * @param status
     * @return список задач по статусу
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    /**
     * Изменяет статус задачи
     * @param id
     * @param status
     * @return новый статус задачи
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        return taskService.updateTaskStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Удаляет задачу
     * @param id
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
