package com.supung.requirement.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
public class ApiError {
	private HttpStatus status;
	private String message;
}
