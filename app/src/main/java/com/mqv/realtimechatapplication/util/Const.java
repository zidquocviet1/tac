package com.mqv.realtimechatapplication.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public final class Const {
    private static final int PORT = 443; // 443 for Https and 8080 or 80 for Http
    public static final String BASE_IP = "192.168.100.25";
//    public static final String BASE_IP = "192.168.240.109";
    public static final String BASE_URL = "https://" + BASE_IP + ":" + PORT + "/api/v1/";
    public static final Long NETWORK_TIME_OUT = 15L;
    public static final String CONTENT_TYPE = "application/json";
    public static final String PHONE_REGEX_PATTERN = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZER = "Authorizer";
    public static final String DEFAULT_AUTHORIZER = "firebase";
    public static final String MULTIPART_TYPE = "multipart/form-data";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String IMAGE_FILE_NAME_PATTERN = "yyyyMMdd_HHmmss"; // example: 09/02/2021 13:01:30 -> 20210902_130130
    public static final String DUMMY_FIRST_CHAT_PREFIX = "DUMMY_FIRST_CHAT";
    public static final String WELCOME_CHAT_PREFIX = "WELCOME_CHAT";

    // Preference Key
    public static final String KEY_PREF_FCM_TOKEN = "fcm_token";
    public static final String KEY_PREF_NOTIFICATION_STATUS = "pref_notification_status";

    // DATABASE
    public static final String DATABASE_NAME = "tac_database";
    public static final int DEFAULT_CHAT_PAGING_SIZE = 40;
    public static final int DEFAULT_CONVERSATION_PAGING_SIZE = 20;

    // OTP Activity
    public static final String EXTRA_VERIFICATION_ID = "verification_id";
    public static final String EXTRA_RESEND_TOKEN = "resend_token";
    public static final String EXTRA_RESEND_PHONE_NUMBER = "resend_phone_number";
    public static final Long PHONE_AUTH_TIME_OUT = 60L;

    // Main Activity
    public static final String EXTRA_USER_INFO = "user_info";

    public static final String GROUP_KEY_SENT_FRIEND_NOTIFICATION = "com.mqv.tac.group.SENT_FRIEND";
    public static final String GROUP_KEY_ACCEPT_FRIEND_NOTIFICATION = "com.mqv.tac.group.ACCEPT_FRIEND";

    // Notification KEY data payload
    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_CHANNEL_ID = "channelId";
    public static final String KEY_CHANNEL_NAME = "channelName";
    public static final String KEY_ACTION_CLICK = "actionClick";
    public static final String KEY_IMAGE_URL = "imageUrl";
    public static final String KEY_UID = "user_id";
    public static final String KEY_AGENT_ID = "agent_id";
    public static final String DEFAULT_NEW_FRIEND_REQUEST = "newFriendRequest";
    public static final String DEFAULT_ACCEPTED_FRIEND_REQUEST = "acceptedFriendRequest";

    // Conversation notification channel config
    public static final String CONVERSATION_CHANNEL_ID = "conversation";
    public static final String CONVERSATION_CHANNEL_NAME = "Conversation Notification";

    private static final String DUMMIES_IMAGES_DOMAIN = BASE_URL + "user/photo/";
    public static final String[] DUMMIES_IMAGES_URL = new String[]{
            DUMMIES_IMAGES_DOMAIN + encode("beyonce-super-bowl-messika-vignette.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("Cristiano_Ronaldo_2018.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("cuc-tinh-y-2.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("david-beckham-footballer-smile-face-wallpaper-preview.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("dich-le-nhiet-ba-7.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("dien-vien-trung-quoc-cuc-tinh-y.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("GettyImages_1152687796.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("hieuminh250-1345801305.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("thuonghai10-1345801306.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("toni-kroos-internat-volkswagen-183634082.jpg"),
            DUMMIES_IMAGES_DOMAIN + encode("zvY94dFUiqKwu1PMVsw5_thatnordicguyredo.jpg"),

    };

    private static String encode(String s){
        var byteArray = Base64.encodeBase64(("dummies/" + s).getBytes(StandardCharsets.UTF_8));
        return new String(byteArray);
    }
}
