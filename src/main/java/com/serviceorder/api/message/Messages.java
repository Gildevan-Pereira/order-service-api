package com.serviceorder.api.message;

public class Messages {

	// Generic Error
	public static final String GENERIC_ERROR = "600#An error has ocurred, please try again!";
	
	// Validation Errors
	public static final String INVALID = "700#Received value is invalid!";
	public static final String REQUIRED = "701#This field is required!";
	
	// Entity Not Found Errors	
	public static final String CLIENT_NOT_FOUND = "800#Client not found!";
	public static final String SERVICE_ORDER_NOT_FOUND = "800#Service Order not found!";
	public static final String ADDRESS_NOT_FOUND = "800#Address not found!";
	public static final String SERVICE_NOT_FOUND = "800#Service not found!";
	public static final String TECHNICIAN_NOT_FOUND = "800#Technician not found!";
	public static final String CATEGORY_NOT_FOUND = "800#Category not found!";

	// Operation Not Allowed
	public static final String SERVICE_ALREADY_STARTED = "900#Service already started!";
	public static final String SERVICE_ALREADY_FINISHED = "901#Service already finished!";
}
