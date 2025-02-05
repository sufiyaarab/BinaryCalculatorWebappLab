package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

    @GetMapping("/add")
    public String addString(@RequestParam(name="operand1", defaultValue="0") String operand1,
                            @RequestParam(name="operand2", defaultValue="0") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.add(number1, number2).getValue();
    }


    @GetMapping("/add_json")
    public BinaryAPIResult addJSON(@RequestParam(name="operand1") String operand1,
                                   @RequestParam(name="operand2") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "add", number2, Binary.add(number1, number2));
    }

    @GetMapping("/and")
    public String and(@RequestParam(name="operand1") String operand1,
                      @RequestParam(name="operand2") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.binaryAND(number1, number2).getValue();
    }

    @GetMapping("/or")
    public String or(@RequestParam(name="operand1") String operand1,
                     @RequestParam(name="operand2") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.binaryOR(number1, number2).getValue();
    }

    @GetMapping("/multi")
    public String multiply(@RequestParam(name="operand1") String operand1,
                           @RequestParam(name="operand2") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.binaryMULTI(number1, number2).getValue();
    }
}
