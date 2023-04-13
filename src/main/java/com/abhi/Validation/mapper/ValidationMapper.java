package com.abhi.Validation.mapper;

import com.abhi.Validation.entity.ValidatedData;
import com.abhi.Validation.externalsvc.ruledatasvc.DataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValidationMapper {
    ValidatedData convertDataDTOToValidatedData(DataDTO fileDTO1);
}
