package com.howard.smartbee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.howard.smartbee.domain.Clint;
import com.howard.smartbee.repository.ClintRepository;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.UpdateClintVo;


class ClintServceTest {
	
	@InjectMocks
	private ClintServce clintServce;
		
	@Mock
	private ClintRepository clintRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void test_save_success() {
		ClintVo clint = new ClintVo();
		clint.setEmail("howardkan@gmail.com");
		clint.setName("Howard");
		clint.setPhone("0930018622");
		clintServce.batchCreate(Arrays.asList(clint));
		verify(clintRepository, times(1)).saveAll(ArgumentMatchers.any()); 
	}
	
	@Test
	void test_update_success() {
		Clint clint = getClint();
		Optional<Clint> opt = Optional.of(clint);
		when(clintRepository.findById(1L))
		.thenReturn(opt);
		when(clintRepository.save(ArgumentMatchers.any()))
		.thenReturn(clint);
		UpdateClintVo updateClintVo = new UpdateClintVo();
		updateClintVo.setId(1L);		
		clintServce.update(updateClintVo);
		verify(clintRepository, times(1)).save(ArgumentMatchers.any()); 
	}

	private Clint getClint() {
		Clint clint = new Clint();
		clint.setEmail("howardkan@gmail.com");
		clint.setName("Howard");
		clint.setPhone("0933444777");
		clint.setId(1L);
		return clint;
	}

	@Test
	void test_read_success() {
		when(clintRepository.findAll())
		.thenReturn(getClints());
		List<ClintVo> result = clintServce.read(null);
		ClintVo clint = new ClintVo();
		clint.setEmail("howardkan@gmail.com");
		clint.setName("Howard");
		clint.setId(1L);
		ClintVo expect = result.get(0);
		assertAll("expect",
	            () -> assertEquals("howardkan@gmail.com", expect.getEmail()),
	            () -> assertEquals("Howard", expect.getName()),
	            () -> assertEquals("0933444777", expect.getPhone())
	        );
	}
	
	@Test
	void test_delete_success() {
		clintServce.delete(1L);
		verify(clintRepository, times(1))
		.deleteById(1L); 
	}
	
	private List<Clint> getClints() {
		Clint clint = getClint();
		List<Clint> result = new ArrayList<>();
		result.add(clint);
		return result;
	}

}
