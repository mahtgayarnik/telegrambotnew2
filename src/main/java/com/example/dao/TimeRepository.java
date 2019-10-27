package com.example.dao;

import com.example.entity.TimeEntity;

public interface TimeRepository {

    TimeEntity getTime(String name);

    void updateTime(String time);
}
