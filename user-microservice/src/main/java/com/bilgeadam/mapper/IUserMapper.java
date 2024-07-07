package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {


    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(UserProfileSaveRequestDto dto);

    List<UserProfileFindAllResponseDto> toUserProfileFindAllResponseDto(List<UserProfile> userProfiles);

}
