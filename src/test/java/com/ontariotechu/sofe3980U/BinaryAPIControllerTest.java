package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
	
	@Test
	public void addMissingOperands() throws Exception {
		// Case 1: Missing operand2 (should treat as "0")
		this.mvc.perform(get("/add").param("operand1", "1101"))
			.andExpect(status().isOk())
			.andExpect(content().string("1101")); // 1101 + 0 = 1101

		// Case 2: Missing operand1 (should treat as "0")
		this.mvc.perform(get("/add").param("operand2", "1010"))
			.andExpect(status().isOk())
			.andExpect(content().string("1010")); // 0 + 1010 = 1010

		// Case 3: Both operands missing (should return "0")
		this.mvc.perform(get("/add"))
			.andExpect(status().isOk())
			.andExpect(content().string("0")); // 0 + 0 = 0
	}


    @Test
    public void addMissingParameters() throws Exception {
        this.mvc.perform(get("/add")) // No parameters passed
            .andExpect(status().isOk())
            .andExpect(content().string("0")); // Default behavior, assuming empty binary numbers are treated as zero
    }

    @Test
    public void addLeadingZeros() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "000101").param("operand2", "000011"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000")); // 101 (5) + 11 (3) = 1000 (8)
    }
	
	@Test
    public void binaryANDTest() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1101").param("operand2", "1011"))
            .andExpect(status().isOk())
            .andExpect(content().string("1001")); // 1101 & 1011 = 1001
    }

    @Test
    public void binaryORTest() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1101").param("operand2", "1011"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111")); // 1101 | 1011 = 1111
    }

    @Test
    public void binaryMULTITest() throws Exception {
        this.mvc.perform(get("/multi").param("operand1", "11").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string("110")); // 11 * 10 = 110
    }

	
	
}