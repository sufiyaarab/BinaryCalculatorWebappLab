package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand1Focused", false));
    }

    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1", "111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand1Focused", true));
    }

    @Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", "111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void postAdditionWithDifferentLengths() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "+").param("operand2", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110")); // 101 + 1 = 110
    }

    @Test
    public void postSubtraction() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "-").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("Error")); // Expecting "Error" for unsupported subtraction
    }

    @Test
    public void postInvalidInput() throws Exception {
        this.mvc.perform(post("/").param("operand1", "102").param("operator", "+").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("Error")); // Invalid binary input
    }
	@Test
    public void binaryMULTITest1() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111")); // 101 * 11 = 1111
    }

    @Test
    public void binaryMULTITest2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "110").param("operator", "*").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1100")); // 110 * 10 = 1100
    }
	
    @Test
    public void binaryORControllerTest() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010").param("operator", "|").param("operand2", "0110"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110")); // 1010 | 0110 = 1110
    }

    @Test
    public void binaryMULTIControllerTest() throws Exception {
        this.mvc.perform(post("/").param("operand1", "11").param("operator", "*").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110")); // 11 * 10 = 110
    }
	
	@Test
	public void binaryANDControllerTest_withPadding() throws Exception {
		// Perform a binary AND operation using the MockMvc
		this.mvc.perform(post("/").param("operand1", "1101").param("operator", "&").param("operand2", "1011"))
			.andExpect(status().isOk())
			.andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1001")); // 1101 & 1011 = 1001
	}

	
	
}
