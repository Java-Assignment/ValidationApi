package com.abhi.Validation.mapper;

import com.abhi.Validation.entity.File;
import com.abhi.Validation.externalsvc.ruledatasvc.FileRuleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {


    File convertFileRuleDTOToFile(FileRuleDTO fileDTO1);
}
