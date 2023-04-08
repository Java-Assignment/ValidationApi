package com.abhi.Validation.service;

import com.abhi.Validation.entity.File;
import com.abhi.Validation.exception.InvalidFileNumberException;
import com.abhi.Validation.externalsvc.formuladatasvc.FileFormulaDTO;
import com.abhi.Validation.externalsvc.formuladatasvc.FormulaRefDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.FileRuleDTO;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleOneDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleTwoDataSvc;
import com.abhi.Validation.mapper.FileMapper;
import com.abhi.Validation.repo.ValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private FileMapper fileMapper;
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
        FileFormulaDTO fileFormulaDTO= formulaRefDataSvc.getByFileNumber(fileNumber);
        if (fileFormulaDTO != null) {
            FileRuleDTO fileDTO1 = ruleOneDataSvc.getByFileNumber(fileNumber);
            FileRuleDTO fileDTO2 = ruleTwoDataSvc.getByFileNumber(fileNumber);
            if (fileDTO1 != null && fileDTO2 != null) {
                File file1 = fileMapper.convertFileRuleDTOToFile(fileDTO1);
                validationRepo.save(file1);
                return "Both RuleOne and RuleTwo success";
            } else if (fileDTO1 != null && fileDTO2 == null) {
                return "RuleOne success and ruleTwo failure";
            } else if (fileDTO1 == null && fileDTO2 != null){
                return "RuleOne failure and ruleTwo success";
            }else{
                return "RuleOne failure and ruleTwo Success";
            }
        } else {
            throw new InvalidFileNumberException("Invalid file number entered");
        }

    }
}
