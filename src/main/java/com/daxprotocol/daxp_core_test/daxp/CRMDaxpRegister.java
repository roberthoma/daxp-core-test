package com.daxprotocol.daxp_core_test.daxp;

import org.daxprotocol.core.annotation.DaxpMsg;
import org.daxprotocol.core.annotation.DaxpRegister;
import org.daxprotocol.core.annotation.DaxpTag;
import org.springframework.stereotype.Service;


@Service
@DaxpRegister("CMR")
public class CRMDaxpRegister {

    public static final int CUSTOMER_ENTITY = 2000;

    @DaxpTag(description = "Customer id")
    public static final int CUSTOMER_ID = 2001;

    public static final int CUSTOMER_FIRST_NAME = 2002;
    public static final int CUSTOMER_SURNAME = 2003;
    public static final int CUSTOMER_EMAIL = 2004;
    public static final int CUSTOMER_TOWN = 2005;
    public static final int CUSTOMER_TELEPHONE = 2006;
    public static final int CUSTOMER_TYPE = 2007;


    public static final int CONTRACT_TAG = 3000;
    public static final int CONTRACT_ID = 3201;
    public static final int CONTRACT_NO = 3203;
    public static final int CONTRACT_AMOUNT = 3202;
    public static final int CONTRACT_STATUS = 3205;


    @DaxpMsg(description = "Customer Data")
    public static final String  CRM_DATA         =  "CRM.DATA";

    @DaxpMsg(description = "Request for Customer Data", respMsg = {CRM_DATA})
    public static final String  CRM_DATA_REQ     =  "CRM.REQ";


    @DaxpMsg(description = "New Customer")
    public static final String  CRM_INSERT       =  "CRM.NEW";

    @DaxpMsg(description = "Update Customer")
    public static final String  CRM_UPDATE       =  "CRM.UPDATE";

    @DaxpMsg(description = "Delete Customer")
    public static final String  CRM_DELETE       =  "CRM.DELETE";

    public static final int TEST_COLLECTION_ENUM_6001 = 6001;

}
