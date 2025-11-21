package com.daxprotocol.daxp_core_test.api;


import com.daxprotocol.daxp_core_test.daxp.AppDaxDictionary;
import jakarta.annotation.PostConstruct;
import org.daxprotocol.core.dictionary.DaxMessageDicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMessage {

    @Autowired AppDaxDictionary dictionary;

    public static final String  CRM_DATA_REQ     =  "CRM.DR"; // 	REQ 	Request for Customer Data
    public static final String  CRM_DATA         =  "CRM.DD"; // 	REs 	Customer Data
    public static final String  CRM_INSERT       =  "CRM.DI"; // 	REs 	New Customer
    public static final String  CRM_UPDATE       =  "CRM.DU"; // 	REs 	New Customer

    public static final String  CNT_DATA_REQ     =  "CNT.DR"; // 	REQ 	Request for a contract data
    public static final String  CNT_DATA         =  "CNT.DD"; // 	RES 	Contract data
    public static final String  CNT_INSERT       =  "CNT.DI"; // 	RES 	Contract data


    @PostConstruct
    private void init(){

        dictionary.putMsgItem(new DaxMessageDicItem(CRM_DATA_REQ, "Request for Customer Data"));
        dictionary.putMsgItem(new DaxMessageDicItem(CRM_DATA, "Customer Data"));
        dictionary.putMsgItem(new DaxMessageDicItem(CRM_INSERT, "New Customer"));

    }





}
