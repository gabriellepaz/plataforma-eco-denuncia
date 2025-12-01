package io.github.websterrodrigues.ms_api_complaint.dto.mapper;

import io.github.websterrodrigues.ms_api_complaint.dto.ComplaintDTO;
import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    Complaint toEntity(ComplaintDTO dto);

    ComplaintDTO toDto(Complaint complaint);
}
