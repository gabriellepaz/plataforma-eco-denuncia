package io.github.websterrodrigues.ms_api_comment.dto.mapper;

import io.github.websterrodrigues.ms_api_comment.dto.CommentDTO;
import io.github.websterrodrigues.ms_api_comment.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDTO dto);
    CommentDTO toDto(Comment comment);
}
