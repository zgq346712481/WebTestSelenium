package model.casejson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CaseSuit implements Serializable {

    private String testCaseName;
    private String testCaseID;

}
