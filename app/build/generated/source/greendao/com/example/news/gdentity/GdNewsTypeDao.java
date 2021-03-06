package com.example.news.gdentity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "news_types".
*/
public class GdNewsTypeDao extends AbstractDao<GdNewsType, Long> {

    public static final String TABLENAME = "news_types";

    /**
     * Properties of entity GdNewsType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Subid = new Property(1, int.class, "subid", false, "SUBID");
        public final static Property SubType = new Property(2, String.class, "subType", false, "SUB_TYPE");
    }


    public GdNewsTypeDao(DaoConfig config) {
        super(config);
    }
    
    public GdNewsTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"news_types\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"SUBID\" INTEGER NOT NULL ," + // 1: subid
                "\"SUB_TYPE\" TEXT);"); // 2: subType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"news_types\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GdNewsType entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getSubid());
 
        String subType = entity.getSubType();
        if (subType != null) {
            stmt.bindString(3, subType);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GdNewsType entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getSubid());
 
        String subType = entity.getSubType();
        if (subType != null) {
            stmt.bindString(3, subType);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public GdNewsType readEntity(Cursor cursor, int offset) {
        GdNewsType entity = new GdNewsType( //
            cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // subid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // subType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GdNewsType entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setSubid(cursor.getInt(offset + 1));
        entity.setSubType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GdNewsType entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GdNewsType entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GdNewsType entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
