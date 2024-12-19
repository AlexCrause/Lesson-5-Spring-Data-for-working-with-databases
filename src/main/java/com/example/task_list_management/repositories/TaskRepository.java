package com.example.task_list_management.repositories;

import com.example.task_list_management.models.Task;
import com.example.task_list_management.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Поиск задач по статусу
     * @param status
     * @return список задач по статусу
     */
    List<Task> findByStatus(TaskStatus status);

}
