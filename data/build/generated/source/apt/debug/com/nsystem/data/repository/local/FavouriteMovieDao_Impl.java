package com.nsystem.data.repository.local;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nsystem.data.entity.FavouriteEntity;
import io.reactivex.Observable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavouriteMovieDao_Impl implements FavouriteMovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFavouriteEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFavouriteEntity;

  public FavouriteMovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavouriteEntity = new EntityInsertionAdapter<FavouriteEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Favourite`(`movieId`,`posterPath`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavouriteEntity value) {
        stmt.bindLong(1, value.getMovieId());
        if (value.getPosterPath() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPosterPath());
        }
      }
    };
    this.__deletionAdapterOfFavouriteEntity = new EntityDeletionOrUpdateAdapter<FavouriteEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Favourite` WHERE `movieId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavouriteEntity value) {
        stmt.bindLong(1, value.getMovieId());
      }
    };
  }

  @Override
  public Long addFavouriteMovie(final FavouriteEntity favouriteEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfFavouriteEntity.insertAndReturnId(favouriteEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Integer unFavouriteMovie(final FavouriteEntity favouriteEntity) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfFavouriteEntity.handle(favouriteEntity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Observable<List<FavouriteEntity>> getFavouriteMovieList() {
    final String _sql = "SELECT * FROM Favourite";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"Favourite"}, new Callable<List<FavouriteEntity>>() {
      @Override
      public List<FavouriteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final List<FavouriteEntity> _result = new ArrayList<FavouriteEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FavouriteEntity _item;
            _item = new FavouriteEntity();
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            _item.setMovieId(_tmpMovieId);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            _item.setPosterPath(_tmpPosterPath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public FavouriteEntity getFavouriteMovie(final int movieId) {
    final String _sql = "SELECT * FROM Favourite WHERE movieId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
      final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
      final FavouriteEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new FavouriteEntity();
        final int _tmpMovieId;
        _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
        _result.setMovieId(_tmpMovieId);
        final String _tmpPosterPath;
        _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
        _result.setPosterPath(_tmpPosterPath);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
