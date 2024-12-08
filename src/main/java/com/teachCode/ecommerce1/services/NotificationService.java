package com.teachCode.ecommerce1.services;


import com.teachCode.ecommerce1.dto.request.NotificationDto;
import com.teachCode.ecommerce1.dto.response.DtoNotification;
import com.teachCode.ecommerce1.entities.Notification;
import com.teachCode.ecommerce1.repositories.NotificationRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	public NotificationService(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Transactional
	public String addNotification(NotificationDto notificationDto) {

		if (notificationDto != null && notificationDto.message() != null) {
			Notification notification = Notification.builder().datePosted(LocalDate.now())
					.message(notificationDto.message()).userId(notificationDto.userId())
					.status(notificationDto.status()).build();

			notificationRepository.save(notification);
			return "Notification has been successfully saved.";
		} else {
			return "Fields are null.";
		}
	}

	public List<DtoNotification> getNotificationsByIdOrGeneral(String id) {
		return notificationRepository.findByIdAndByAll(id).orElse(Collections.emptyList()).stream()
				.map(n -> DtoNotification.builder().userId(n.getUserId()).datePosted(n.getDatePosted())
						.message(n.getMessage()).status(n.isStatus()).build())
				.collect(Collectors.toList());
	}

}
