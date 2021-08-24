package com.mqv.realtimechatapplication.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mqv.realtimechatapplication.databinding.FragmentConversationBinding;
import com.mqv.realtimechatapplication.network.model.Conversation;
import com.mqv.realtimechatapplication.ui.adapter.ConversationAdapter;
import com.mqv.realtimechatapplication.ui.adapter.RankUserConversationAdapter;
import com.mqv.realtimechatapplication.ui.fragment.viewmodel.ConversationFragmentViewModel;
import com.mqv.realtimechatapplication.util.Logging;
import com.mqv.realtimechatapplication.util.MessageStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ConversationFragment extends BaseSwipeFragment<ConversationFragmentViewModel, FragmentConversationBinding>{
    private ConversationAdapter adapter;
    private RankUserConversationAdapter rankUserConversationAdapter;

    @Override
    public void binding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mBinding = FragmentConversationBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    public Class<ConversationFragmentViewModel> getViewModelClass() {
        return ConversationFragmentViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    @Override
    public void setupObserver() {
        if (mViewModel != null){
            mViewModel.getConversationList().observe(this, conversations -> {
                if (conversations != null && !conversations.isEmpty()){
                    adapter.submitList(conversations);
                }
            });

            mViewModel.getRemoteUserList().observe(this, remoteUsers -> {
                if (remoteUsers != null && !remoteUsers.isEmpty()){
                    rankUserConversationAdapter.submitList(remoteUsers);
                }
            });
        }
    }

    @NonNull
    @Override
    public SwipeRefreshLayout getSwipeLayout() {
        return mBinding.swipeMessages;
    }

    @Override
    public void onRefresh() {
        Logging.show("onReload is called");
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            var listConversation = Arrays.asList(
                    new Conversation(1L, "Phạm Thảo Duyên", "You: ok", LocalDateTime.now(), MessageStatus.RECEIVED),
                    new Conversation(2L, "Phạm Băng Băng", "You: haha", LocalDateTime.now(), MessageStatus.SEEN),
                    new Conversation(3L, "Triệu Lệ Dĩnh", "You: wo ai ni", LocalDateTime.now(), MessageStatus.SEEN),
                    new Conversation(4L, "Ngô Diệc Phàm", "You: 2 phut hon", LocalDateTime.now(), MessageStatus.NOT_RECEIVED),
                    new Conversation(5L, "Lưu Đức Hoa", "You: hao le", LocalDateTime.now(), MessageStatus.RECEIVED)
            );
            adapter.submitList(listConversation);
            stopRefresh();
        }, 2000);
    }

    private void setupRecyclerView(){
        var listConversation = new ArrayList<Conversation>();

        adapter = new ConversationAdapter(listConversation, getContext());
        adapter.submitList(listConversation);

        rankUserConversationAdapter = new RankUserConversationAdapter(getContext());

        mBinding.recyclerMessages.setAdapter(adapter);
        mBinding.recyclerMessages.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerMessages.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerMessages.setNestedScrollingEnabled(false);
        mBinding.recyclerMessages.setHasFixedSize(false);

        mBinding.recyclerRankChat.setAdapter(rankUserConversationAdapter);
        mBinding.recyclerRankChat.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        mBinding.recyclerRankChat.setItemAnimator(new DefaultItemAnimator());
    }
}
