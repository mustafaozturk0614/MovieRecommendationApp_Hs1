package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto(Auth auth);

    @Mapping(source = "id",target = "authId")
    UserProfileSaveRequestDto toUserProfileSaveRequestDto(Auth auth);


}
