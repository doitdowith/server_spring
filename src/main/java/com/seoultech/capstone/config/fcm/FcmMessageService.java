//package com.seoultech.capstone.config.fcm;
//
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.FirebaseMessagingException;
//import java.util.List;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FcmMessageService {
//
//  private final FirebaseMessaging firebaseMessaging;
//
//  public FcmMessageService(FirebaseMessaging firebaseMessaging) {
//    this.firebaseMessaging = firebaseMessaging;
//  }
//
//  public void unsubscribeFromTopic() throws FirebaseMessagingException {
//    List<String> deviceTokens = deviceTokens();
//    firebaseMessaging.unsubscribeFromTopic(deviceTokens, Topic.NOTICE.toString());
//  }
//
//  public void subscribeToTopic() throws FirebaseMessagingException {
//    List<String> deviceTokens = deviceTokens();
//    firebaseMessaging.subscribeToTopic(deviceTokens, Topic.NOTICE.toString());
//  }
//
//  private List<String> deviceTokens() {
//    return null;
//  }
//}
