package com.example.news.gdentity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.news.gdentity.GdNewsType;

import com.example.news.gdentity.GdNewsTypeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig gdNewsTypeDaoConfig;

    private final GdNewsTypeDao gdNewsTypeDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        gdNewsTypeDaoConfig = daoConfigMap.get(GdNewsTypeDao.class).clone();
        gdNewsTypeDaoConfig.initIdentityScope(type);

        gdNewsTypeDao = new GdNewsTypeDao(gdNewsTypeDaoConfig, this);

        registerDao(GdNewsType.class, gdNewsTypeDao);
    }
    
    public void clear() {
        gdNewsTypeDaoConfig.clearIdentityScope();
    }

    public GdNewsTypeDao getGdNewsTypeDao() {
        return gdNewsTypeDao;
    }

}