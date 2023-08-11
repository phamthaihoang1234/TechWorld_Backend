package com.example.TechWorld.apiHandle;

import com.example.TechWorld.model.Notification;
import com.example.TechWorld.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationApiTest {

    @InjectMocks
    NotificationApiHandle notificationApi;

    @Mock
    NotificationRepository notificationRepository;



    @Test
    public void testGetAll_NotificationsExist() {
        List<Notification> expectedNotifications = new ArrayList<>();
        Notification notification1 = new Notification();
        notification1.setId(1L);
        Notification notification2 = new Notification();
        notification2.setId(2L);
        expectedNotifications.add(notification1);
        expectedNotifications.add(notification2);
        when(notificationRepository.findByOrderByIdDesc()).thenReturn(expectedNotifications);
        ResponseEntity<List<Notification>> response = notificationApi.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNotifications, response.getBody());
    }

    @Test
    public void testGetAll_NoNotifications() {
        when(notificationRepository.findByOrderByIdDesc()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Notification>> response = notificationApi.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void Given_ExistingNotification_When_post_Then_ReturnBadRequest() {
        Notification notification = new Notification();
        notification.setId(1L);
        when(notificationRepository.existsById(notification.getId())).thenReturn(true);
        ResponseEntity<Notification> response = notificationApi.post(notification);
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void Given_NonExistingNotification_When_post_Then_ReturnSuccess() {
        Notification notification = new Notification();
        notification.setId(1L);
        when(notificationRepository.existsById(notification.getId())).thenReturn(false);
        Notification savedNotification = new Notification();
        when(notificationRepository.save(notification)).thenReturn(savedNotification);
        ResponseEntity<Notification> response = notificationApi.post(notification);
        assertEquals(200, response.getStatusCodeValue());
        assertSame(savedNotification, response.getBody());
    }

    @Test
    public void testPut_NotificationExists() {
        Long notificationId = 1L;
        Notification notification = new Notification();
        notification.setId(notificationId);
        notification.setStatus(false);
        when(notificationRepository.existsById(notificationId)).thenReturn(true);
        when(notificationRepository.getById(notificationId)).thenReturn(notification);
        ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
        when(notificationRepository.save(notificationCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));
        ResponseEntity<Notification> response = notificationApi.put(notificationId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getStatus());
        assertEquals(notification, response.getBody());
    }

    @Test
    public void testPut_NotificationNotFound() {
        Long notificationId = 1L;
        when(notificationRepository.existsById(notificationId)).thenReturn(false);
        ResponseEntity<Notification> response = notificationApi.put(notificationId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    public void testReadAll() {
        ResponseEntity<Void> response = notificationApi.readAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificationRepository).readAll();
    }

}