package com.example.dao;

import com.example.entity.VoteEntity;

import java.util.List;

public interface VoteRepository {

    void updateVote(VoteEntity vote);

    void saveVote(VoteEntity vote);

    List<VoteEntity> getAllVote();

    VoteEntity getVote(String question);

    void disableAllVotes();

    VoteEntity getActiveVote();
}
