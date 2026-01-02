package com.IAbouzaid.Tasks.Dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer Count,
        Double progress,
        List<TaskDto> tasks

) {
}
