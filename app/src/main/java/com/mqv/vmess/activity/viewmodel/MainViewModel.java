package com.mqv.vmess.activity.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.auth.FirebaseUser;
import com.mqv.vmess.data.repository.ConversationRepository;
import com.mqv.vmess.data.repository.FriendRequestRepository;
import com.mqv.vmess.data.repository.NotificationRepository;
import com.mqv.vmess.data.repository.PeopleRepository;
import com.mqv.vmess.data.repository.UserRepository;
import com.mqv.vmess.data.result.Result;
import com.mqv.vmess.network.model.User;
import com.mqv.vmess.network.model.type.ConversationStatusType;
import com.mqv.vmess.reactive.RxHelper;
import com.mqv.vmess.ui.data.People;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends AbstractMainViewModel {
    private final NotificationRepository notificationRepository;
    private final ConversationRepository conversationRepository;

    private final MutableLiveData<Integer> notificationBadgeResult = new MutableLiveData<>();
    private final MutableLiveData<Integer> conversationBadgeResult = new MutableLiveData<>();

    @Inject
    public MainViewModel(UserRepository userRepository,
                         FriendRequestRepository friendRequestRepository,
                         PeopleRepository peopleRepository,
                         NotificationRepository notificationRepository,
                         ConversationRepository conversationRepository) {
        super(userRepository, friendRequestRepository, peopleRepository, notificationRepository);

        this.notificationRepository = notificationRepository;
        this.conversationRepository = conversationRepository;

        onFirstLoad();
        observeConversationUnreadBadge();
    }

    @Override
    public void onRefresh() {
        // default implementation
    }

    public LiveData<Uri> getUserPhotoUrl() {
        return Transformations.map(getFirebaseUser(), user -> {
            if (user == null)
                return null;
            return user.getPhotoUrl();
        });
    }

    public LiveData<Result<User>> getRemoteUserResultSafe() {
        return getRemoteUserResult();
    }

    public LiveData<List<People>> getListPeopleSafe() {
        return getListPeople();
    }

    public LiveData<Integer> getNotificationBadgeResult() {
        return notificationBadgeResult;
    }

    public LiveData<Integer> getConversationBadgeResult() {
        return conversationBadgeResult;
    }

    private void loadNotificationBadge() {
        cd.add(notificationRepository.observeUnreadFriendNotification()
                .compose(RxHelper.applyFlowableSchedulers())
                .subscribe(notificationBadgeResult::postValue, t -> notificationBadgeResult.setValue(0)));
    }

    private void observeConversationUnreadBadge() {
        // Need to sync the currently limit conversation item list
        // DistinctUntilChanged to avoid load many times
        cd.add(conversationRepository.observeUnreadConversation(ConversationStatusType.INBOX, 20)
                .compose(RxHelper.applyFlowableSchedulers())
                .subscribe(map -> {
                    FirebaseUser user = getFirebaseUser().getValue();

                    if (user != null) {
                        long unread = map.values()
                                         .stream()
                                         .filter(c -> !c.getSeenBy().contains(user.getUid()) &&
                                                      c.getSenderId() != null &&
                                                      !c.getSenderId().equals(user.getUid()))
                                         .count();
                        conversationBadgeResult.postValue(Long.valueOf(unread).intValue());
                    }
                }, t -> notificationBadgeResult.setValue(0)));
    }

    public void onFirstLoad() {
        loadRemoteUserUsingNBR();
        loadAllPeople();
        loadNotificationBadge();
        loadAllRemoteNotification();
    }
}
