package com.stone.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.stone.dtos.ContactDto;
import com.stone.repositories.ContactRepository;
import com.stone.services.ContactService;



@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

	
	@Mock
	private ContactRepository contactRepository;
	
	@InjectMocks
    private ContactService contactService;
	
	@Before
	public void setup() {
		when(contactRepository.findById(1l)).thenReturn(Optional.of(TestUtils.dummyContact()));
	}
	                                
    @Test
    public void testQuery()  {
        ContactDto dto = contactService.getContactById(1l);
        assertNotNull(dto);
        assertEquals(1l, (long)dto.getId());
        
    }

}
