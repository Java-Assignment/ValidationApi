package com.abhi.Validation.service;

import com.abhi.Validation.dto.FileDTO;
import com.abhi.Validation.exception.InvalidFileNumberException;
import com.abhi.Validation.externalsvc.formuladatasvc.FormulaRefDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleOneDataSvc;
import com.abhi.Validation.externalsvc.ruledatasvc.RuleTwoDataSvc;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private FormulaRefDataSvc formulaRefDataSvc;
    @Autowired
    private RuleOneDataSvc ruleOneDataSvc;
    @Autowired
    private RuleTwoDataSvc ruleTwoDataSvc;

    @Override
    public String CheckValidation(String fileNumber) throws InvalidFileNumberException {
        FileDTO fileDTO = formulaRefDataSvc.getByFileNumber(fileNumber);
        if (fileDTO != null) {
            FileDTO fileDTO1 = ruleOneDataSvc.getByFileNumber(fileNumber);
            FileDTO fileDTO2 = ruleTwoDataSvc.getByFileNumber(fileNumber);
            if (fileDTO1 != null && fileDTO2 != null) {
                return "Both RuleOne and RuleTwo success";
            }
            else if (fileDTO1 != null && fileDTO2 == null) {
                return "RuleOne success and ruleTwo failure";
            }
            else {
                return "RuleOne failure and ruleTwo success";
            }
        }
        else {
            throw new InvalidFileNumberException("Invalid file number entered");
        }

    }
}
