package com.tanvir.hellonotification.task.application.port.in;

import com.tanvir.hellonotification.task.application.port.in.dto.response.TaskDetailResponseDto;
import com.tanvir.hellonotification.task.application.port.in.dto.response.TaskListResponseDto;
import reactor.core.publisher.Mono;

public interface TaskManagementUseCase {
    Mono<TaskDetailResponseDto> getTaskDetailById(String taskId);
    Mono<TaskListResponseDto> getTaskList();
}
