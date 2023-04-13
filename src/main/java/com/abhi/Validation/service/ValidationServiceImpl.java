package com.abhi.Validation.service;

import com.abhi.Validation.entity.ValidatedData;
import com.abhi.Validation.exception.InvalidFileNumberException;
import com.abhi.Validation.externalsvc.formuladatasvc.FormulaDataDTO;
import com.abhi.Validation.externalsvc.formuladatasvc.FormulaRefDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.DataDTO;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleOneDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleTwoDataSvc;
import com.abhi.Validation.mapper.ValidationMapper;
import com.abhi.Validation.repo.ValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private ValidationMapper validationMapper;
    @Autowired
    private FormulaRefDataSvc formulaRefDataSvc;
    @Autowired
    private RuleOneDataSvc ruleOneDataSvc;
    @Autowired
    private RuleTwoDataSvc ruleTwoDataSvc;
    @Autowired
    private ValidationRepo validationRepo;

    @Override
    public String CheckValidation(String fileNumber) throws InvalidFileNumberException {
        FormulaDataDTO formulaDataDTO = formulaRefDataSvc.getByFileNumber(fileNumber);
        if (formulaDataDTO != null) {
            DataDTO dataDTO1 = ruleOneDataSvc.RuleOneCheck(fileNumber);
            DataDTO dataDTO2 = ruleTwoDataSvc.RuleTwoCheck(fileNumber);
            if (dataDTO1 != null && dataDTO2 != null) {
                ValidatedData validatedData1 = validationMapper.convertDataDTOToValidatedData(dataDTO1);
                validationRepo.save(validatedData1);
                return "Both RuleOne and RuleTwo success";
            } else if (dataDTO1 != null && dataDTO2 == null) {
                return "RuleOne success and ruleTwo failure";
            } else if (dataDTO1 == null && dataDTO2 != null){
                return "RuleOne failure and ruleTwo success";
            }else{
                return "RuleOne failure and ruleTwo Success";
            }
        } else {
            throw new InvalidFileNumberException("Invalid file number entered");
        }

    }
}
