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
    DaxMessageCodec messageCodec;

    @Autowired
    DaxpMsgDispatcher dispatcher;


    private ResponseEntity<String> requestFun(Map<String, String> params, String body){
        params.forEach((s, s2) -> System.out.println(s+"=>"+s2));
        DaxMessage message;
        if (params.containsKey("msgType")){
            message = new DaxMessage(  params.get("msgType"));
        }
        else {
            message = messageCodec.decode(body);
        }

        try {
            DaxMessage respMsg = dispatcher.dispose(message);
            return ResponseEntity.ok().body(messageCodec.encode(respMsg));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/get")
    public ResponseEntity<String> getMessage(@RequestParam(required = false) Map<String, String> params,
                                             @RequestBody(required = false) String body)
    {
        return requestFun(params, body);
    }

    @PostMapping("/post")
    public ResponseEntity<String> postMessage(@RequestParam(required = false) Map<String, String> params,
            @RequestBody(required = false) String body)
    {
        return requestFun(params, body);
    }
}
