package com.mqv.vmess.util;

public final class Const {
    private static final int PORT = 443; // 443 for Https and 8080 or 80 for Http
    public static final String BASE_IP = "192.168.9.100";
    public static final String BASE_URL = "https://" + BASE_IP + ":" + PORT + "/api/v1/";
    public static final Long NETWORK_TIME_OUT = 20L;
    public static final String CONTENT_TYPE = "application/json";
    public static final String PHONE_REGEX_PATTERN = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZER = "Authorizer";
    public static final String DEFAULT_AUTHORIZER = "firebase";
    public static final String MULTIPART_TYPE = "multipart/form-data";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String IMAGE_FILE_NAME_PATTERN = "yyyyMMdd_HHmmss"; // example: 09/02/2021 13:01:30 -> 20210902_130130

    // Preference Key
    public static final String KEY_PREF_FCM_TOKEN = "fcm_token";
    public static final String KEY_PREF_NOTIFICATION_STATUS = "pref_notification_status";
    public static final String KEY_PREF_AUTH_USER_TOKEN = "auth_token";
    public static final String KEY_PREF_AUTH_USER_TOKEN_EXPIRES_TIME = "auth_token_expires_time";

    // DATABASE
    public static final String DATABASE_NAME = "tac_database";
    public static final int DEFAULT_CHAT_PAGING_SIZE = 40;
    public static final int DEFAULT_CONVERSATION_PAGING_SIZE = 20;

    // OTP Activity
    public static final String EXTRA_VERIFICATION_ID = "verification_id";
    public static final String EXTRA_RESEND_TOKEN = "resend_token";
    public static final String EXTRA_RESEND_PHONE_NUMBER = "resend_phone_number";
    public static final Long PHONE_AUTH_TIME_OUT = 60L;
}
