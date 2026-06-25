package com.daxprotocol.daxp_core_test.api;

import org.daxprotocol.core.application.DaxEngine;
import org.daxprotocol.core.model.DaxFrame;
import org.daxprotocol.core.model.DaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/daxp")
public class DaxpRestController {

    @Autowired DaxEngine daxEngine;

    private ResponseEntity<String> requestFun(Map<String, String> params, String body){
        params.forEach((s, s2) -> System.out.println(s+"=>"+s2));
        DaxMessage message;
        try {
        //TODO create frame from params if body is null
        DaxFrame reqFrame = daxEngine.getFrameParser().parseFrame(body);
        DaxFrame respFrame = new DaxFrame();

        respFrame.setPreamble(daxEngine.getPreambleFactory().createRespPreamble(reqFrame));

        daxEngine.getHandlerRegistry().executor(reqFrame,respFrame);


        return ResponseEntity.ok(  daxEngine.getFrameCodec().encode(respFrame));
        }
        catch (Exception e) {
                e.printStackTrace(); //FOR test
                return ResponseEntity.badRequest().build();  //TODO return by DAXP error
            }
    }

    @GetMapping
    public ResponseEntity<String> getMessage(@RequestParam(required = false) Map<String, String> params,
                                             @RequestBody(required = false) String body)
    {
        return requestFun(params, body);
    }

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestParam(required = false) Map<String, String> params,
            @RequestBody(required = false) String body)
    {
        return requestFun(params, body);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteMessage(@RequestParam(required = false) Map<String, String> params,
            @RequestBody(required = false) String body)
    {
        return requestFun(params, body);
    }
}
