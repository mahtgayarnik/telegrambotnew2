package com.example.dao;

import com.example.entity.LanguageEntity;

public interface LanguageRepository {

    void saveLanguage(LanguageEntity language);

    void updateLanguage(LanguageEntity language);

    LanguageEntity getLanguageEntity(Long userTelegramId);
}
