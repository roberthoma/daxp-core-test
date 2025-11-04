package com.daxprotocol.daxp_core_test.api;

import org.daxprotocol.core.codec.DaxMessageCodec;
import org.daxprotocol.core.model.DaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/daxp")
public class DaxpRestController {
    @Autowired
    DaxMessageCodec codec;

    @Autowired
    DaxpMsgDispatcher dispatcher;

// TODO get & post using params  without body


    @GetMapping("/get")
    public ResponseEntity<String> getMessage(@RequestParam(required = false) Map<String, String> params,
            @RequestBody(required = false) String body){
        params.forEach((s, s2) -> System.out.println(s+"=>"+s2));
        try {
            DaxMessage respMsg = dispatcher.dispose(codec.decode(body));
            return ResponseEntity.ok().body(codec.encode(respMsg));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get_par")
    public ResponseEntity<String> getMessagePar(@RequestParam Map<String, String> params ){

        try {
            params.forEach((s, s2) -> System.out.println(s+" "+s2));
            DaxMessage respMsg = dispatcher.disposeByPar ("qqqq");
            return ResponseEntity.ok().body(codec.encode(respMsg));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


//    @PostMapping("/post")
//    public ResponseEntity<String> postMessage(){
//
//        DaxMessage message =  factory.createDictionaryReq();
//
//        return ResponseEntity.ok().body(codec.encode(message));
//
//    }
}
